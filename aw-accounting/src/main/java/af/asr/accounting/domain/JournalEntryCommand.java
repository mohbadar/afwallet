
package af.asr.accounting.domain;

@SuppressWarnings({"unused"})
public final class JournalEntryCommand {

  private Action action;
  private String comment;
  private String createdOn;
  private String createdBy;

  public JournalEntryCommand() {
    super();
  }

  public String getAction() {
    return this.action.name();
  }

  public void setAction(final String action) {
    this.action = Action.valueOf(action);
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

  @SuppressWarnings("WeakerAccess")
  public enum Action {
    RELEASE
  }
}
