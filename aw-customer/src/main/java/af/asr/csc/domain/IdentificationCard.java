
package af.asr.csc.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class IdentificationCard {

  @NotBlank
  private String type;
  @NotBlank
  private String number;
  @NotNull
  @Valid
  private ExpirationDate expirationDate;
  private String issuer;
  private String createdBy;
  private String createdOn;
  private String lastModifiedBy;
  private String lastModifiedOn;

  public IdentificationCard() {
    super();
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(final String number) {
    this.number = number;
  }

  public ExpirationDate getExpirationDate() {
    return this.expirationDate;
  }

  public void setExpirationDate(final ExpirationDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getIssuer() {
    return this.issuer;
  }

  public void setIssuer(final String issuer) {
    this.issuer = issuer;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(String createdOn) {
    this.createdOn = createdOn;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public String getLastModifiedOn() {
    return lastModifiedOn;
  }

  public void setLastModifiedOn(String lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }
}
