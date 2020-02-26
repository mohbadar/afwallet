package af.gov.anar.ebreshna.configuration.nsc.supply_voltage;

import af.gov.anar.ebreshna.configuration.nsc.supply_voltage.SupplyVoltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyVoltageRepository extends JpaRepository<SupplyVoltage, Long> {
}
