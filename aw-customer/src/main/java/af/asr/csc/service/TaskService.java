package af.asr.csc.service;

import af.asr.csc.domain.TaskDefinition;
import af.asr.csc.mapper.TaskDefinitionMapper;
import af.asr.csc.repository.CustomerRepository;
import af.asr.csc.model.TaskDefinitionEntity;
import af.asr.csc.repository.TaskDefinitionRepository;
import af.asr.csc.repository.TaskInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskDefinitionRepository taskDefinitionRepository;
    private final TaskInstanceRepository taskInstanceRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public TaskService(final TaskDefinitionRepository taskDefinitionRepository,
                       final TaskInstanceRepository taskInstanceRepository,
                       final CustomerRepository customerRepository) {
        super();
        this.taskDefinitionRepository = taskDefinitionRepository;
        this.taskInstanceRepository = taskInstanceRepository;
        this.customerRepository = customerRepository;
    }

    public Boolean taskDefinitionExists(final String identifier) {
        return this.taskDefinitionRepository.existsByIdentifier(identifier);
    }

    public Optional<TaskDefinitionEntity> findByIdentifier(final String identifier) {
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
}