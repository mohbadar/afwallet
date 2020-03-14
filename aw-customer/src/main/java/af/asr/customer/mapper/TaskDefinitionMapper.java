
package af.asr.customer.mapper;

import af.asr.customer.domain.TaskDefinition;
import af.asr.customer.model.TaskDefinitionEntity;
import org.apache.commons.lang.StringUtils;


public final class TaskDefinitionMapper {

  private TaskDefinitionMapper() {
    super();
  }

  public static TaskDefinitionEntity map(final TaskDefinition taskDefinition) {
    final TaskDefinitionEntity taskDefinitionEntity = new TaskDefinitionEntity();
    taskDefinitionEntity.setIdentifier(taskDefinition.getIdentifier());
    taskDefinitionEntity.setType(taskDefinition.getType());
    taskDefinitionEntity.setName(taskDefinition.getName());
    taskDefinitionEntity.setDescription(taskDefinition.getDescription());
    taskDefinitionEntity.setAssignedCommands(StringUtils.join(taskDefinition.getCommands(), ";"));
    taskDefinitionEntity.setMandatory(taskDefinition.getMandatory());
    taskDefinitionEntity.setPredefined(taskDefinition.getPredefined());
    return taskDefinitionEntity;
  }

  public static TaskDefinition map(final TaskDefinitionEntity taskDefinitionEntity) {
    final TaskDefinition taskDefinition = new TaskDefinition();
    taskDefinition.setIdentifier(taskDefinitionEntity.getIdentifier());
    taskDefinition.setType(taskDefinitionEntity.getType());
    taskDefinition.setName(taskDefinitionEntity.getName());
    taskDefinition.setDescription(taskDefinitionEntity.getDescription());
    taskDefinition.setCommands(taskDefinitionEntity.getAssignedCommands().split(";"));
    taskDefinition.setMandatory(taskDefinitionEntity.getMandatory());
    taskDefinition.setPredefined(taskDefinitionEntity.getPredefined());
    return taskDefinition;
  }
}
