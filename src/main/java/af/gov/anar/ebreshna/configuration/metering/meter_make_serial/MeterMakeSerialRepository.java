package af.gov.anar.ebreshna.configuration.metering.meter_make_serial;

import af.gov.anar.ebreshna.configuration.metering.meter_make_serial.MeterMakeSerial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterMakeSerialRepository extends JpaRepository<MeterMakeSerial, Long> {
}
