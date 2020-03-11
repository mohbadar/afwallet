package af.gov.anar.ebreshna.configuration.metering.cycle;

import af.gov.anar.ebreshna.configuration.metering.cycle.CycleConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleRepository extends JpaRepository<CycleConfiguration, Long> {
}
