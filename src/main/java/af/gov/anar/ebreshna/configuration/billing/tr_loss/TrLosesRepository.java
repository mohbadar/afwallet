package af.gov.anar.ebreshna.configuration.billing.tr_loss;

import af.gov.anar.ebreshna.configuration.billing.tr_loss.TrLossesConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrLosesRepository extends JpaRepository<TrLossesConfiguration, Long> {
}
