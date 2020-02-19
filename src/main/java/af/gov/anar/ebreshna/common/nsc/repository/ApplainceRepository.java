package af.gov.anar.ebreshna.common.nsc.repository;

import af.gov.anar.ebreshna.common.nsc.model.ActivityMaster;
import af.gov.anar.ebreshna.common.nsc.model.ApplianceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplainceRepository extends JpaRepository<ApplianceMaster, Long> {
}
