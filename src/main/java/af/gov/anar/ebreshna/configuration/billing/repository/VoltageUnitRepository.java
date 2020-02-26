package af.gov.anar.ebreshna.configuration.billing.repository;

import af.gov.anar.ebreshna.configuration.billing.model.TrLossesConfiguration;
import af.gov.anar.ebreshna.configuration.billing.model.VoltageUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoltageUnitRepository extends JpaRepository<VoltageUnit, Long> {
}
