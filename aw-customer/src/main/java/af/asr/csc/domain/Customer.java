
package af.asr.csc.domain;

import af.gov.anar.lang.validation.date.DateOfBirth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Customer {

  public enum Type {
    PERSON,
    BUSINESS
  }

  public enum State {
    PENDING,
    ACTIVE,
    LOCKED,
    CLOSED
  }

  @NotBlank
  private String identifier;
  @NotNull
  private Type type;
  @NotBlank
  private String givenName;
  private String middleName;
  @NotBlank
  private String surname;
  @NotNull
  private DateOfBirth dateOfBirth;
  @NotNull
  private Boolean member;
  private String accountBeneficiary;
  private String referenceCustomer;
  private String assignedOffice;
  private String assignedEmployee;
  @NotNull
  @Valid
  private Address address;
  @Valid
  private List<ContactDetail> contactDetails;
  private State currentState;
  private String applicationDate;
//  private List<Value> customValues;
  private String createdBy;
  private String createdOn;
  private String lastModifiedBy;
  private String lastModifiedOn;

}
