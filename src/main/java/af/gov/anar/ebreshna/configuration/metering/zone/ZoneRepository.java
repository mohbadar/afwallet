package af.gov.anar.ebreshna.configuration.metering.zone;

import af.gov.anar.ebreshna.configuration.metering.zone.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
