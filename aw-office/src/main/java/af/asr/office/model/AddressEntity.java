
package af.asr.office.model;

import javax.persistence.*;

@Entity
@Table(name = "office_addresses", schema = "office")
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column(name = "street")
  private String street;
  @Column(name = "city")
  private String city;
  @Column(name = "region")
  private String region;
  @Column(name = "postal_code")
  private String postalCode;
  @Column(name = "country_code")
  private String countryCode;
  @Column(name = "country")
  private String country;
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "office_id")
  private OfficeEntity office;

  public AddressEntity() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public OfficeEntity getOffice() {
    return office;
  }

  public void setOffice(OfficeEntity office) {
    this.office = office;
  }
}
