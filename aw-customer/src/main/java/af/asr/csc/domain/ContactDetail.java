
package af.asr.csc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ContactDetail {

  public enum Type {
    EMAIL,
    PHONE,
    MOBILE
  }

  public enum Group {
    BUSINESS,
    PRIVATE
  }

  @NotNull
  private Type type;
  @NotNull
  private Group group;
  @NotBlank
  private String value;
  @Min(1)
  @Max(127)
  private Integer preferenceLevel;
  private Boolean validated;

}
