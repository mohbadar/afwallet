
package af.asr.payroll.domain;

import java.util.ArrayList;
import java.util.List;

public class PayrollPaymentPage {

  private List<PayrollPayment> payrollPayments;
  private Integer totalPages;
  private Long totalElements;

  public PayrollPaymentPage() {
    super();
  }

  public List<PayrollPayment> getPayrollPayments() {
    return this.payrollPayments;
  }

  public void setPayrollPayments(final List<PayrollPayment> payrollPayments) {
    this.payrollPayments = payrollPayments;
  }

  public Integer getTotalPages() {
    return this.totalPages;
  }

  public void setTotalPages(final Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Long getTotalElements() {
    return this.totalElements;
  }

  public void setTotalElements(final Long totalElements) {
    this.totalElements = totalElements;
  }

  public void add(final PayrollPayment payrollPayment) {
    if (this.payrollPayments == null) {
      this.payrollPayments = new ArrayList<>();
    }
    this.payrollPayments.add(payrollPayment);
  }
}
