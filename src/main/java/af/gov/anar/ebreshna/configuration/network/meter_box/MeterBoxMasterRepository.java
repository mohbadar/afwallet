package af.gov.anar.ebreshna.configuration.network.meter_box;

import af.gov.anar.ebreshna.configuration.network.meter_box.MeterBoxMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterBoxMasterRepository extends JpaRepository<MeterBoxMaster, Long> {
}
