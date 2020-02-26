package af.gov.anar.ebreshna.configuration.network.substation;

import af.gov.anar.ebreshna.configuration.network.substation.SubstationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstationMasterRepository extends JpaRepository<SubstationMaster, Long> {
}
