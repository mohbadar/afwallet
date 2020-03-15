
package af.asr.office.repository;

import af.asr.office.model.ExternalReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExternalReferenceRepository extends JpaRepository<ExternalReferenceEntity, Long> {

  Optional<ExternalReferenceEntity> findByOfficeIdentifierAndType(final String officeIdentifier, final String type);

  List<ExternalReferenceEntity> findByOfficeIdentifier(final String officeIdentifier);

  void deleteByOfficeIdentifier(final String officeIdentifier);
}
