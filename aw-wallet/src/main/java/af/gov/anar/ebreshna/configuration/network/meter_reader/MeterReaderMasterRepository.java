package af.gov.anar.ebreshna.configuration.network.meter_reader;

import af.gov.anar.ebreshna.configuration.network.meter_reader.MeterReaderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReaderMasterRepository extends JpaRepository<MeterReaderMaster, Long> {
}
