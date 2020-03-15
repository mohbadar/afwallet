
package af.asr.customer.catalog.repository;

import af.asr.customer.catalog.model.FieldEntity;
import af.asr.customer.catalog.model.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

  void deleteByField(final FieldEntity fieldEntity);
}
