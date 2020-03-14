
package af.asr.csc.domain;

import java.util.Objects;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDocument {
  @ValidIdentifier
  private String identifier;

  @Length(max = 4096)
  private String description;

  private boolean completed;
  private String createdBy;
  private String createdOn;


}
