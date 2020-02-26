package af.gov.anar.ebreshna.configuration.metering.meter_status;

import af.gov.anar.ebreshna.configuration.metering.meter_status.MeterStatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterStatusRepository extends JpaRepository<MeterStatusMaster, Long> {
}
