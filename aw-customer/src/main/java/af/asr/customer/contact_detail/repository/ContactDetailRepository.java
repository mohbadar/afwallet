package af.asr.customer.contact_detail.repository;

import af.asr.customer.contact_detail.model.ContactDetailEntity;
import af.asr.customer.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetailEntity, Long> {

    List<ContactDetailEntity> findByCustomer(final CustomerEntity customerEntity);
}