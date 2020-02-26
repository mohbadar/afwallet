package af.gov.anar.ebreshna.configuration.office.field_staff;

import af.gov.anar.ebreshna.configuration.office.field_staff.FieldStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldStaffRepository extends JpaRepository<FieldStaff, Long> {
}
