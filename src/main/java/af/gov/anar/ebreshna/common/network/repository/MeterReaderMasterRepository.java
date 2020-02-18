package af.gov.anar.ebreshna.common.network.repository;

import af.gov.anar.ebreshna.common.network.model.AreaMaster;
import af.gov.anar.ebreshna.common.network.model.MeterReaderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReaderMasterRepository extends JpaRepository<MeterReaderMaster, Long> {
}