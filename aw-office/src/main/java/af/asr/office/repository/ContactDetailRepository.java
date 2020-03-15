
package af.asr.office.repository;

import af.asr.office.model.ContactDetailEntity;
import af.asr.office.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetailEntity, Long> {

  List<ContactDetailEntity> findByEmployeeOrderByPreferenceLevelAsc(final EmployeeEntity employeeEntity);
}
