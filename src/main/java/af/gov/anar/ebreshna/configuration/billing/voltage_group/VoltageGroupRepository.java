package af.gov.anar.ebreshna.configuration.billing.voltage_group;

import af.gov.anar.ebreshna.configuration.billing.voltage_group.VoltageGroupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoltageGroupRepository extends JpaRepository<VoltageGroupMaster, Long> {
}
