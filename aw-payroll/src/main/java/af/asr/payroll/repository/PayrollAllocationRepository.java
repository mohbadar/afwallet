
package af.asr.payroll.repository;


import af.asr.payroll.model.PayrollAllocationEntity;
import af.asr.payroll.model.PayrollConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollAllocationRepository extends JpaRepository<PayrollAllocationEntity, Long> {

  void deleteByPayrollConfiguration(final PayrollConfigurationEntity payrollConfigurationEntity);

  List<PayrollAllocationEntity> findByPayrollConfiguration(final PayrollConfigurationEntity payrollConfigurationEntity);
}
