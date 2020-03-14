
package af.asr.csc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


  public void setAction(final String action) {
    this.action = Action.valueOf(action.toUpperCase());
  }


}