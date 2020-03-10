package af.gov.anar.ebreshna.configuration.metering.meter_activity;

import af.gov.anar.ebreshna.configuration.metering.meter_activity.MeteringActivityMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeteringActivityRepository extends JpaRepository<MeteringActivityMaster, Long> {
}
