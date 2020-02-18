package af.gov.anar.ebreshna.common.office.repository;

import af.gov.anar.ebreshna.common.office.model.FieldStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldStaffRepository extends JpaRepository<FieldStaff, Long> {
}
