package af.gov.anar.ebreshna.configuration.nsc.repository;

import af.gov.anar.ebreshna.configuration.nsc.model.ApplianceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplainceRepository extends JpaRepository<ApplianceMaster, Long> {
}
