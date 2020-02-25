package af.gov.anar.ebreshna.configuration.metering.repository;

import af.gov.anar.ebreshna.configuration.metering.model.ActivityRelation;
import af.gov.anar.ebreshna.configuration.metering.model.MeterStatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterStatusRepository extends JpaRepository<MeterStatusMaster, Long> {
}
