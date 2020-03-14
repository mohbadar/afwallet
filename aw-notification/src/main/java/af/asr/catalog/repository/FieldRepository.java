
package af.asr.catalog.repository;

import af.asr.catalog.model.CatalogEntity;
import af.asr.catalog.model.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {

  Optional<FieldEntity> findByCatalogAndIdentifier(final CatalogEntity catalog, final String identifier);
}
