
package af.asr.payroll.domain;

import java.util.List;
import javax.validation.Valid;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;
import org.hibernate.validator.constraints.NotEmpty;

public class PayrollCollectionSheet {

  @ValidIdentifier(maxLength = 34)
  private String sourceAccountNumber;
  @NotEmpty
  @Valid
  private List<PayrollPayment> payrollPayments;

  public PayrollCollectionSheet() {
    super();
  }

  public String getSourceAccountNumber() {
    return this.sourceAccountNumber;
  }

  public void setSourceAccountNumber(final String sourceAccountNumber) {
    this.sourceAccountNumber = sourceAccountNumber;
  }

  public List<PayrollPayment> getPayrollPayments() {
    return this.payrollPayments;
  }

  public void setPayrollPayments(final List<PayrollPayment> payrollPayments) {
    this.payrollPayments = payrollPayments;
  }
}
