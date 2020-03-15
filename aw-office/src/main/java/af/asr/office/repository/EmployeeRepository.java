
package af.asr.office.repository;

import af.asr.office.model.EmployeeEntity;
import af.asr.office.model.OfficeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

  EmployeeEntity findByIdentifier(final String identifier);

  @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM EmployeeEntity e WHERE e.identifier = :identifier")
  Boolean existsByIdentifier(@Param("identifier") final String identifier);

  Page<EmployeeEntity> findByAssignedOffice(final OfficeEntity assignedOffice, final Pageable pageable);

  Page<EmployeeEntity> findByIdentifierContaining(String term, Pageable pageRequest);

  @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM EmployeeEntity e WHERE e.assignedOffice = :office")
  Boolean existsByAssignedOffice(@Param("office") final OfficeEntity assignedOffice);
}
