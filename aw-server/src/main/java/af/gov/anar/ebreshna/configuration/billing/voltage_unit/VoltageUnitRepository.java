package af.gov.anar.ebreshna.configuration.billing.voltage_unit;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.voltage_unit.VoltageUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoltageUnitRepository extends JpaRepository<VoltageUnit, Long> , RevisionRepository<VoltageUnit, Long, Integer> {
}
