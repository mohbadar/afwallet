
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
public final class Address {

  @NotBlank
  private String street;
  @NotBlank
  private String city;
  private String region;
  private String postalCode;
  @NotBlank
  private String countryCode;
  @NotBlank
  private String country;

}
