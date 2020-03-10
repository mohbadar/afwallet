package af.gov.anar.ebreshna.configuration.office.office;

import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeMasterRepository extends JpaRepository<OfficeMaster, Long> {
}
