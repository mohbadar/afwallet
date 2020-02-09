package af.gov.anar.ebreshna.office.repository;

import af.gov.anar.ebreshna.office.model.OfficeMaster;
import af.gov.anar.ebreshna.office.model.PremisesSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeMasterRepository extends JpaRepository<OfficeMaster, Long> {
}
