
package af.asr.csc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class TaskInstance {

  @NotBlank
  private String taskIdentifier;
  private String comment;
  private String executedOn;
  private String executedBy;


}
