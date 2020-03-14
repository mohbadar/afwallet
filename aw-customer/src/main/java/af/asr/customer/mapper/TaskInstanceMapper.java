
package af.asr.customer.mapper;

import af.asr.customer.domain.TaskInstance;
import af.asr.customer.model.CustomerEntity;
import af.asr.customer.model.TaskDefinitionEntity;
import af.asr.customer.model.TaskInstanceEntity;
import af.gov.anar.lang.validation.date.DateConverter;
import org.springframework.stereotype.Component;

@Component
public class TaskInstanceMapper {

  public TaskInstanceMapper() {
    super();
  }

  public static TaskInstanceEntity create(final TaskDefinitionEntity taskDefinition, final CustomerEntity customer) {
    final TaskInstanceEntity taskInstanceEntity = new TaskInstanceEntity();
    taskInstanceEntity.setTaskDefinition(taskDefinition);
    taskInstanceEntity.setCustomer(customer);
    return taskInstanceEntity;
  }

  public static TaskInstance map(final TaskInstanceEntity taskInstanceEntity) {
    final TaskInstance taskInstance = new TaskInstance();
    taskInstance.setTaskIdentifier(taskInstanceEntity.getTaskDefinition().getIdentifier());
    taskInstance.setComment(taskInstanceEntity.getComment());
    if (taskInstanceEntity.getExecutedOn() != null) {
      taskInstance.setExecutedOn(DateConverter.toIsoString(taskInstanceEntity.getExecutedOn()));
    }
    taskInstance.setExecutedBy(taskInstanceEntity.getExecutedBy());
    return taskInstance;
  }
}
