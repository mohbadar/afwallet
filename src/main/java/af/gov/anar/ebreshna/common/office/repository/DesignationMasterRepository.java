package af.gov.anar.ebreshna.common.office.repository;

import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationMasterRepository extends JpaRepository<DesignationMaster, Long> {
}
