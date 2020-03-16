
package af.asr.payroll.domain;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PayrollPayment {

  @ValidIdentifier
  private String customerIdentifier;
  @NotNull
  private String employer;
  @NotNull
  @DecimalMin("0.001")
  @DecimalMax("9999999999.99999")
  private BigDecimal salary;
  private Boolean processed;
  private String message;

  public PayrollPayment() {
    super();
  }

  public String getCustomerIdentifier() {
    return this.customerIdentifier;
  }

  public void setCustomerIdentifier(final String customerIdentifier) {
    this.customerIdentifier = customerIdentifier;
  }

  public String getEmployer() {
    return this.employer;
  }

  public void setEmployer(final String employer) {
    this.employer = employer;
  }

  public BigDecimal getSalary() {
    return this.salary;
  }

  public void setSalary(final BigDecimal salary) {
    this.salary = salary;
  }

  public Boolean getProcessed() {
    return this.processed;
  }

  public void setProcessed(final Boolean processed) {
    this.processed = processed;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
