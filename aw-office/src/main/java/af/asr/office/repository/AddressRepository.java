
package af.asr.office.repository;

import af.asr.office.model.AddressEntity;
import af.asr.office.model.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

  Optional<AddressEntity> findByOffice(final OfficeEntity officeEntity);
}
