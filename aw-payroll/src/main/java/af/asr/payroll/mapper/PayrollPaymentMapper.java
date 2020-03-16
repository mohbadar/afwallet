
package af.asr.payroll.mapper;


import af.asr.payroll.domain.PayrollPayment;
import af.asr.payroll.model.PayrollPaymentEntity;

public class PayrollPaymentMapper {

  private PayrollPaymentMapper() {
    super();
  }

  public static PayrollPayment map(final PayrollPaymentEntity payrollPaymentEntity) {
    final PayrollPayment payrollPayment = new PayrollPayment();
    payrollPayment.setCustomerIdentifier(payrollPaymentEntity.getCustomerIdentifier());
    payrollPayment.setEmployer(payrollPaymentEntity.getEmployer());
    payrollPayment.setSalary(payrollPaymentEntity.getSalary());
    payrollPayment.setProcessed(payrollPaymentEntity.getProcessed());
    payrollPayment.setMessage(payrollPaymentEntity.getMessage());
    return payrollPayment;
  }
}
