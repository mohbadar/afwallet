
package af.asr.payroll.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;

public class PayrollConfiguration {

  @ValidIdentifier(maxLength = 34)
  private String mainAccountNumber;
  @Valid
  private List<PayrollAllocation> payrollAllocations = new ArrayList<>();
  private String createdBy;
  private String createdOn;
  private String lastModifiedBy;
  private String lastModifiedOn;

  public PayrollConfiguration() {
    super();
  }

  public String getMainAccountNumber() {
    return this.mainAccountNumber;
  }

  public void setMainAccountNumber(final String mainAccountNumber) {
    this.mainAccountNumber = mainAccountNumber;
  }

  public List<PayrollAllocation> getPayrollAllocations() {
    return this.payrollAllocations;
  }

  public void setPayrollAllocations(final List<PayrollAllocation> payrollAllocations) {
    this.payrollAllocations = payrollAllocations;
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

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public void setLastModifiedBy(final String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public String getLastModifiedOn() {
    return this.lastModifiedOn;
  }

  public void setLastModifiedOn(final String lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }
}
