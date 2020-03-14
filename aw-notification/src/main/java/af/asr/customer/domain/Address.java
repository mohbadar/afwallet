
package af.asr.customer.domain;

import org.hibernate.validator.constraints.NotBlank;

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

  public Address() {
    super();
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(final String street) {
    this.street = street;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getRegion() {
    return this.region;
  }

  public void setRegion(final String region) {
    this.region = region;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(final String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }
}
