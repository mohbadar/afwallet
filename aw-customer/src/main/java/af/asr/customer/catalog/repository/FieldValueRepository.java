
package af.asr.customer.catalog.repository;

import af.asr.customer.catalog.model.FieldEntity;
import af.asr.customer.catalog.model.FieldValueEntity;
import af.asr.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldValueRepository extends JpaRepository<FieldValueEntity, Long> {

  List<FieldValueEntity> findByCustomer(final CustomerEntity customer);

  void deleteByCustomer(final CustomerEntity customer);

  Optional<FieldValueEntity> findByField(final FieldEntity fieldEntity);
}
