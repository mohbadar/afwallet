
package af.asr.catalog.repository;

import af.asr.catalog.model.FieldEntity;
import af.asr.catalog.model.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

  void deleteByField(final FieldEntity fieldEntity);
}
