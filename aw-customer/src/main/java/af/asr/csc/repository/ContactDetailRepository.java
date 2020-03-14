package af.asr.csc.repository;

import af.asr.csc.model.ContactDetailEntity;
import af.asr.csc.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetailEntity, Long> {

    List<ContactDetailEntity> findByCustomer(final CustomerEntity customerEntity);
}