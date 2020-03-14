package af.asr.customer.identification_card.repository;

import af.asr.customer.identification_card.model.IdentificationCardEntity;
import af.asr.customer.identification_card.model.IdentificationCardScanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentificationCardScanRepository extends JpaRepository<IdentificationCardScanEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN 'true' ELSE 'false' END FROM IdentificationCardScanEntity i WHERE i.identifier = :identifier AND i.identificationCard = :identificationCard")
    Boolean existsByIdentifierAndIdentificationCard(@Param("identifier") final String identifier, @Param("identificationCard") final IdentificationCardEntity identificationCardEntity);

    Optional<IdentificationCardScanEntity> findByIdentifierAndIdentificationCard(final String identifier, final IdentificationCardEntity identificationCardEntity);

    List<IdentificationCardScanEntity> findByIdentificationCard(final IdentificationCardEntity identificationCardEntity);
}