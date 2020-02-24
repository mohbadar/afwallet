package af.gov.anar.ebreshna.configuration.office.repository;

import af.gov.anar.ebreshna.configuration.office.model.GovCodeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GovCodeMasterRepository extends JpaRepository<GovCodeMaster, Long> {
}
