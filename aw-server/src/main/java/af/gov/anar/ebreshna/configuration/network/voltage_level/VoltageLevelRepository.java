package af.gov.anar.ebreshna.configuration.network.voltage_level;

import af.gov.anar.ebreshna.configuration.network.voltage_level.VoltageLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoltageLevelRepository extends JpaRepository<VoltageLevel, Long> {
}
