package af.gov.anar.ebreshna.office.repository;

import af.gov.anar.ebreshna.office.model.DesignationMaster;
import af.gov.anar.ebreshna.office.model.PremisesSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationMasterRepository extends JpaRepository<DesignationMaster, Long> {
}
