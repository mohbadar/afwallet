
package af.asr.office.repository;

import af.asr.office.model.OfficeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {

  Optional<OfficeEntity> findByIdentifier(final String identifier);

  @Query("SELECT CASE WHEN COUNT(o) > 0 THEN 'true' ELSE 'false' END FROM OfficeEntity o WHERE o.identifier = :identifier")
  Boolean existsByIdentifier(@Param("identifier") final String identifier);

  @Query("SELECT CASE WHEN COUNT(o) > 0 THEN 'true' ELSE 'false' END FROM OfficeEntity o WHERE o.parentOfficeId = :parentOfficeId")
  Boolean existsByParentOfficeId(@Param("parentOfficeId") final Long parentOfficeId);

  Page<OfficeEntity> findByParentOfficeIdIsNull(final Pageable pageable);

  Page<OfficeEntity> findByParentOfficeId(final Long parentOfficeId, final Pageable pageable);

  Page<OfficeEntity> findByIdentifierContainingOrNameContaining(final String identifier, final String name, final Pageable pageable);
}
