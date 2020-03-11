package af.gov.anar.ebreshna.configuration.nsc.meterial_estimate;

import af.gov.anar.ebreshna.configuration.nsc.meterial_estimate.MaterialEstimateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialEstimateRepository extends JpaRepository<MaterialEstimateMaster, Long> {
}
