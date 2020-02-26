package af.gov.anar.ebreshna.configuration.network.feeder;

import af.gov.anar.ebreshna.configuration.network.feeder.FeederMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeederMasterRepository extends JpaRepository<FeederMaster, Long> {
}
