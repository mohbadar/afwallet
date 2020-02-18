package af.gov.anar.ebreshna.common.office.repository;

import af.gov.anar.ebreshna.common.office.model.OfficeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeMasterRepository extends JpaRepository<OfficeMaster, Long> {
}
