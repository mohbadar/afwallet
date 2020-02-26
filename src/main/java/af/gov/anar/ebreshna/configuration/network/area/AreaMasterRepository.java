package af.gov.anar.ebreshna.configuration.network.area;

import af.gov.anar.ebreshna.configuration.network.area.AreaMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaMasterRepository extends JpaRepository<AreaMaster, Long> {
}
