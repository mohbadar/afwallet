
package af.asr.csc.domain;

import java.util.List;

public class ProcessStep {

  private Command command;
  private List<TaskDefinition> taskDefinitions;

  public ProcessStep() {
    super();
  }

  public Command getCommand() {
    return this.command;
  }

  public void setCommand(final Command command) {
    this.command = command;
  }

  public List<TaskDefinition> getTaskDefinitions() {
    return this.taskDefinitions;
  }

  public void setTaskDefinitions(final List<TaskDefinition> taskDefinitions) {
    this.taskDefinitions = taskDefinitions;
  }
}
