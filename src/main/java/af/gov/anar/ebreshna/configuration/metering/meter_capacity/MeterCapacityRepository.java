package af.gov.anar.ebreshna.configuration.metering.meter_capacity;

import af.gov.anar.ebreshna.configuration.metering.meter_capacity.MeterCapacityMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterCapacityRepository extends JpaRepository<MeterCapacityMaster, Long> {
}
