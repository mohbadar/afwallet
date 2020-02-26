package af.gov.anar.ebreshna.configuration.payment.fee_penalty;

import af.gov.anar.ebreshna.configuration.payment.fee_penalty.FeePenaltyConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeePenaltyConfigurationRepository extends JpaRepository<FeePenaltyConfiguration, Long> {
}
