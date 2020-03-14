package af.asr.csc.service;

import af.asr.csc.domain.Command;
import af.asr.csc.domain.TaskDefinition;
import af.asr.csc.domain.TaskInstance;
import af.asr.csc.mapper.*;
import af.asr.csc.model.CustomerEntity;
import af.asr.csc.model.TaskInstanceEntity;
import af.asr.csc.repository.CustomerRepository;
import af.asr.csc.model.TaskDefinitionEntity;
import af.asr.csc.repository.TaskDefinitionRepository;
import af.asr.csc.repository.TaskInstanceRepository;
import af.asr.infrastructure.service.UserService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskDefinitionRepository taskDefinitionRepository;
    private final TaskInstanceRepository taskInstanceRepository;
    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Autowired
    public TaskService(final TaskDefinitionRepository taskDefinitionRepository,
                       final TaskInstanceRepository taskInstanceRepository,
                       final CustomerRepository customerRepository, UserService userService) {
        super();
        this.taskDefinitionRepository = taskDefinitionRepository;
        this.taskInstanceRepository = taskInstanceRepository;
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    public Boolean taskDefinitionExists(final String identifier) {
        return this.taskDefinitionRepository.existsByIdentifier(identifier);
    }

    public Optional<TaskDefinition> findByIdentifier(final String identifier) {
        final TaskDefinitionEntity taskDefinitionEntity = this.taskDefinitionRepository.findByIdentifier(identifier);
        if (taskDefinitionEntity != null) {
            return Optional.of(TaskDefinitionMapper.map(taskDefinitionEntity));
        } else {
            return Optional.empty();
        }
    }

    public List<TaskDefinition> fetchAll() {
        return this.taskDefinitionRepository.findAll()
                .stream()
                .map(TaskDefinitionMapper::map)
                .collect(Collectors.toList());
    }

    public List<TaskDefinition> findTasksByCustomer(final String customerIdentifier, Boolean includeExecuted) {
        return customerRepository.findByIdentifier(customerIdentifier)
                .map(taskInstanceRepository::findByCustomer)
                .orElse(Collections.emptyList())
                .stream()
                .filter(taskInstanceEntity -> (includeExecuted ? true : taskInstanceEntity.getExecutedBy() == null))
                .map(taskInstanceEntity -> TaskDefinitionMapper.map(taskInstanceEntity.getTaskDefinition()))
                .collect(Collectors.toList());
    }


    @Transactional
    public String createTaskDefinition(final TaskDefinition taskDefinition) {
        this.taskDefinitionRepository.save(TaskDefinitionMapper.map(taskDefinition));
        return taskDefinition.getIdentifier();
    }

    @Transactional
    public String updateTaskDefinition(final String identifier, TaskDefinition taskDefinition) {
        final TaskDefinitionEntity taskDefinitionEntity = this.taskDefinitionRepository.findByIdentifier(identifier);

        taskDefinitionEntity.setName(taskDefinition.getName());
        taskDefinitionEntity.setDescription(taskDefinition.getDescription());
        taskDefinitionEntity.setAssignedCommands(StringUtils.join(taskDefinition.getCommands(), ";"));
        taskDefinitionEntity.setMandatory(taskDefinition.getMandatory());
        taskDefinitionEntity.setPredefined(taskDefinition.getPredefined());

        this.taskDefinitionRepository.save(taskDefinitionEntity);

        return identifier;
    }

    @Transactional
    public String addTaskToCustomer(final String customerIdentifier, String taskIdentifier ) {
        final TaskDefinitionEntity taskDefinitionEntity =
                this.taskDefinitionRepository.findByIdentifier(taskIdentifier);

        final CustomerEntity customerEntity = findCustomerEntityOrThrow(customerIdentifier);

        this.taskInstanceRepository.save(TaskInstanceMapper.create(taskDefinitionEntity, customerEntity));

        return customerIdentifier;
    }

    @Transactional
    public String executeTaskForCustomer(final String customerIdentifier, String taskIdentifier) {
        final CustomerEntity customerEntity = findCustomerEntityOrThrow(customerIdentifier);
        final List<TaskInstanceEntity> taskInstanceEntities = this.taskInstanceRepository.findByCustomer(customerEntity);
        if (taskInstanceEntities != null) {
            final Optional<TaskInstanceEntity> taskInstanceEntityOptional = taskInstanceEntities
                    .stream()
                    .filter(
                            taskInstanceEntity -> taskInstanceEntity.getTaskDefinition().getIdentifier().equals(taskIdentifier)
                                    && taskInstanceEntity.getExecutedBy() == null
                    )
                    .findAny();

            if (taskInstanceEntityOptional.isPresent()) {
                final TaskInstanceEntity taskInstanceEntity = taskInstanceEntityOptional.get();
                taskInstanceEntity.setExecutedBy(userService.getPreferredUsername());
                taskInstanceEntity.setExecutedOn(LocalDateTime.now(Clock.systemUTC()));
                this.taskInstanceRepository.save(taskInstanceEntity);
            }
        }

        return customerIdentifier;
    }

    @Transactional
    public void onCustomerCommand(final CustomerEntity customerEntity, Command.Action action) {
        final List<TaskDefinitionEntity> predefinedTasks =
                this.taskDefinitionRepository.findByAssignedCommandsContaining(action.name());
        if (predefinedTasks != null && predefinedTasks.size() > 0) {
            List<TaskInstanceEntity> taskInstances = new ArrayList<>();
            for (TaskDefinitionEntity taskDefinitionEntity: predefinedTasks)
            {
                if(taskDefinitionEntity.getPredefined())
                {
//                    for(TaskInstance taskInstance: taskDefinitionEntity.get)
                    taskInstances.add(TaskInstanceMapper.create(taskDefinitionEntity, customerEntity));
                }
            }

            this.taskInstanceRepository.saveAll(taskInstances);
        }
    }

    @Transactional
    public Boolean openTasksForCustomerExist(final CustomerEntity customerEntity, final String command) {
        final List<TaskInstanceEntity> taskInstanceEntities = this.taskInstanceRepository.findByCustomer(customerEntity);

        //noinspection SimplifiableIfStatement
        if (taskInstanceEntities != null) {
            return taskInstanceEntities
                    .stream()
                    .filter(taskInstanceEntity -> taskInstanceEntity.getTaskDefinition().getAssignedCommands().contains(command))
                    .filter(taskInstanceEntity -> taskInstanceEntity.getTaskDefinition().getMandatory())
                    .anyMatch(taskInstanceEntity -> taskInstanceEntity.getExecutedBy() == null);
        } else {
            return false;
        }
    }

    private CustomerEntity findCustomerEntityOrThrow(String identifier) {
        return this.customerRepository.findByIdentifier(identifier)
                .orElseThrow(() -> ServiceException.notFound("Customer ''{0}'' not found", identifier));
    }
}