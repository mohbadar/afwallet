
package af.asr.customer.catalog.repository;

import af.asr.customer.catalog.model.CatalogEntity;
import af.asr.customer.catalog.model.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {

  Optional<FieldEntity> findByCatalogAndIdentifier(final CatalogEntity catalog, final String identifier);
}
