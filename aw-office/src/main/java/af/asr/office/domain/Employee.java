
package af.asr.office.domain;


import af.gov.anar.lang.validation.constraints.ValidIdentifier;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
public class Employee {

  @ValidIdentifier
  private String identifier;
  private String givenName;
  private String middleName;
  private String surname;
  private String assignedOffice;
  @Valid
  private List<ContactDetail> contactDetails;

  public Employee() {
    super();
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getAssignedOffice() {
    return assignedOffice;
  }

  public void setAssignedOffice(String assignedOffice) {
    this.assignedOffice = assignedOffice;
  }

  public List<ContactDetail> getContactDetails() {
    return contactDetails;
  }

  public void setContactDetails(List<ContactDetail> contactDetails) {
    this.contactDetails = contactDetails;
  }
}
