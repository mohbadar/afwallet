
package af.asr.payroll.repository;

import af.asr.payroll.model.PayrollCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollCollectionRepository extends JpaRepository<PayrollCollectionEntity, Long> {
  List<PayrollCollectionEntity> findAllByOrderByCreatedOnDesc();

  Optional<PayrollCollectionEntity> findByIdentifier(String identifier);
}
