package af.gov.anar.ebreshna.configuration.billing.repository;

import af.gov.anar.ebreshna.configuration.billing.enumeration.VoltageGroup;
import af.gov.anar.ebreshna.configuration.billing.model.TrLossesConfiguration;
import af.gov.anar.ebreshna.configuration.billing.model.VoltageGroupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoltageGroupRepository extends JpaRepository<VoltageGroupMaster, Long> {
}
