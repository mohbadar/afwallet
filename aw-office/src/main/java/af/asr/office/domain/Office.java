
package af.asr.office.domain;


import af.gov.anar.lang.validation.constraints.ValidIdentifier;

@SuppressWarnings("unused")
public class Office {

  @ValidIdentifier
  private String identifier;
  private String parentIdentifier;
  private String name;
  private String description;
  private Address address;
  private Boolean externalReferences;

  public Office() {
    super();
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getParentIdentifier() {
    return this.parentIdentifier;
  }

  public void setParentIdentifier(final String parentIdentifier) {
    this.parentIdentifier = parentIdentifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Boolean getExternalReferences() {
    return this.externalReferences;
  }

  public void setExternalReferences(final Boolean externalReferences) {
    this.externalReferences = externalReferences;
  }
}
