package af.gov.anar.ebreshna.configuration.metering.repository;

import af.gov.anar.ebreshna.configuration.metering.model.ActivityMaster;
import af.gov.anar.ebreshna.configuration.metering.model.ActivityRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityMaster, Long> {
}
