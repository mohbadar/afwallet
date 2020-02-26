package af.gov.anar.ebreshna.configuration.office.designation;

import af.gov.anar.ebreshna.configuration.office.designation.DesignationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationMasterRepository extends JpaRepository<DesignationMaster, Long> {
}
