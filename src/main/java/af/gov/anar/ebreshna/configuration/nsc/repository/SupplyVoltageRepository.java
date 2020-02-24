package af.gov.anar.ebreshna.configuration.nsc.repository;

import af.gov.anar.ebreshna.configuration.nsc.model.SupplyVoltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyVoltageRepository extends JpaRepository<SupplyVoltage, Long> {
}
