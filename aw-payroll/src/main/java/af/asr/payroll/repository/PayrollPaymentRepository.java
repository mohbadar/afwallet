
package af.asr.payroll.repository;

import af.asr.payroll.model.PayrollCollectionEntity;
import af.asr.payroll.model.PayrollPaymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollPaymentRepository extends JpaRepository<PayrollPaymentEntity, Long> {
  Page<PayrollPaymentEntity> findByPayrollCollection(final PayrollCollectionEntity payrollCollectionEntity,
                                                     Pageable pageable);
}
