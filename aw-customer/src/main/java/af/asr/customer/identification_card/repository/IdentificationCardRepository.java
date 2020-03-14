package af.asr.customer.identification_card.repository;
import af.asr.customer.customer.CustomerEntity;
import af.asr.customer.identification_card.model.IdentificationCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface IdentificationCardRepository extends JpaRepository<IdentificationCardEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN 'true' ELSE 'false' END FROM IdentificationCardEntity i WHERE i.number = :number")
    Boolean existsByNumber(@Param("number") final String number);

    Optional<IdentificationCardEntity> findByNumber(final String number);

    Stream<IdentificationCardEntity> findByCustomer(final CustomerEntity customerEntity);
}