
package af.asr.catalog.repository;

import af.asr.catalog.model.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogEntity, Long> {

  Optional<CatalogEntity> findByIdentifier(final String identifier);
}
