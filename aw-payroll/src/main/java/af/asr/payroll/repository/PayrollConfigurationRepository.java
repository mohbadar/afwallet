
package af.asr.payroll.repository;

import af.asr.payroll.model.PayrollConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayrollConfigurationRepository extends JpaRepository<PayrollConfigurationEntity, Long> {
  Optional<PayrollConfigurationEntity> findByCustomerIdentifier(final String customerIdentifier);
}
