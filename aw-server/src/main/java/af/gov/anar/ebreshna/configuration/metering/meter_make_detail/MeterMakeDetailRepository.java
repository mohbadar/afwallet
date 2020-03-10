package af.gov.anar.ebreshna.configuration.metering.meter_make_detail;

import af.gov.anar.ebreshna.configuration.metering.meter_make_detail.MeterMakeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterMakeDetailRepository extends JpaRepository<MeterMakeDetail, Long> {
}
