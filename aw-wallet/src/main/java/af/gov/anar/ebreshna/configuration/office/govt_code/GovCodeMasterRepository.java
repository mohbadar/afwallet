package af.gov.anar.ebreshna.configuration.office.govt_code;

import af.gov.anar.ebreshna.configuration.office.govt_code.GovCodeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GovCodeMasterRepository extends JpaRepository<GovCodeMaster, Long> {
}
