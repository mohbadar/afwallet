
package af.asr.csc.domain;

import org.hibernate.validator.constraints.NotBlank;

public final class TaskInstance {

  @NotBlank
  private String taskIdentifier;
  private String comment;
  private String executedOn;
  private String executedBy;

  public TaskInstance() {
    super();
  }

  public String getTaskIdentifier() {
    return taskIdentifier;
  }

  public void setTaskIdentifier(final String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(final String comment) {
    this.comment = comment;
  }

  public String getExecutedOn() {
    return this.executedOn;
  }

  public void setExecutedOn(final String executedOn) {
    this.executedOn = executedOn;
  }

  public String getExecutedBy() {
    return this.executedBy;
  }

  public void setExecutedBy(final String executedBy) {
    this.executedBy = executedBy;
  }
}
