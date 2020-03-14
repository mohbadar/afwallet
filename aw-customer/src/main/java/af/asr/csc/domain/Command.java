
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
public class Command {

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

}
