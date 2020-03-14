
package af.asr.csc.domain;

import javax.validation.constraints.NotNull;

public final class Command {

  public enum Action {
    ACTIVATE,
    LOCK,
    UNLOCK,
    CLOSE,
    REOPEN
  }

  @NotNull
  private Action action;
  private String comment;
  private String createdOn;
  private String createdBy;

  public Command() {
    super();
  }

  public String getAction() {
    return this.action.name();
  }

  public void setAction(final String action) {
    this.action = Action.valueOf(action.toUpperCase());
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(final String comment) {
    this.comment = comment;
  }

  public String getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(final String createdOn) {
    this.createdOn = createdOn;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(final String createdBy) {
    this.createdBy = createdBy;
  }
}
