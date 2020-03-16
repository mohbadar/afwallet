
package af.asr.payroll.domain;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;

import javax.validation.constraints.NotNull;

public class PayrollCollectionHistory {

  @ValidIdentifier
  private String identifier;
  @ValidIdentifier(maxLength = 34)
  private String sourceAccountNumber;
  @ValidIdentifier
  private String createdBy;
  @NotNull
  private String createdOn;

  public PayrollCollectionHistory() {
    super();
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  public String getSourceAccountNumber() {
    return this.sourceAccountNumber;
  }

  public void setSourceAccountNumber(final String sourceAccountNumber) {
    this.sourceAccountNumber = sourceAccountNumber;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(final String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(final String createdOn) {
    this.createdOn = createdOn;
  }
}
