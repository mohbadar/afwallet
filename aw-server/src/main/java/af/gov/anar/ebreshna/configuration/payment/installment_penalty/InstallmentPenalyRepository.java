package af.gov.anar.ebreshna.configuration.payment.installment_penalty;

import af.gov.anar.ebreshna.configuration.payment.installment_penalty.InstallmentPenalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentPenalyRepository extends JpaRepository<InstallmentPenalty, Long> {
}
