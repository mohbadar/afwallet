package af.gov.anar.ebreshna.configuration.nsc.activity;

import af.gov.anar.ebreshna.configuration.nsc.activity.ActivityMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityMaster, Long> {
}
