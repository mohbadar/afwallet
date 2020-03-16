
package af.asr.payroll.mapper;

import af.asr.payroll.domain.PayrollAllocation;
import af.asr.payroll.model.PayrollAllocationEntity;

public class PayrollAllocationMapper {

  private PayrollAllocationMapper() {
    super();
  }

  public static PayrollAllocation map(final PayrollAllocationEntity payrollAllocationEntity) {
    final PayrollAllocation payrollAllocation = new PayrollAllocation();
    payrollAllocation.setAccountNumber(payrollAllocationEntity.getAccountNumber());
    payrollAllocation.setAmount(payrollAllocationEntity.getAmount());
    payrollAllocation.setProportional(payrollAllocationEntity.getProportional());
    return payrollAllocation;
  }

  public static PayrollAllocationEntity map(final PayrollAllocation payrollAllocation) {
    final PayrollAllocationEntity payrollAllocationEntity = new PayrollAllocationEntity();
    payrollAllocationEntity.setAccountNumber(payrollAllocation.getAccountNumber());
    payrollAllocationEntity.setAmount(payrollAllocation.getAmount());
    payrollAllocationEntity.setProportional(payrollAllocation.getProportional());
    return payrollAllocationEntity;
  }
}
