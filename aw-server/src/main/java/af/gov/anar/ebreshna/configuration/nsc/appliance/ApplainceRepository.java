package af.gov.anar.ebreshna.configuration.nsc.appliance;

import af.gov.anar.ebreshna.configuration.nsc.appliance.ApplianceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplainceRepository extends JpaRepository<ApplianceMaster, Long> {
}
