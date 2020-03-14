package af.asr.customer.identification_card.repository;


import af.asr.customer.customer.CustomerEntity;
import af.asr.customer.identification_card.model.PortraitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PortraitRepository extends JpaRepository<PortraitEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM PortraitEntity c WHERE c.customer.identifier = :identifier")
    Boolean existsByIdentifier(@Param("identifier") final String identifier);

    PortraitEntity findByCustomer(final CustomerEntity customerEntity);

    void deleteByCustomer(final CustomerEntity customerEntity);
}