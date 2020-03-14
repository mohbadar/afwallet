package af.asr.csc.repository;

import af.asr.csc.model.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM CustomerEntity c WHERE c.identifier = :identifier")
    Boolean existsByIdentifier(@Param("identifier") final String identifier);

    Page<CustomerEntity> findByIdentifierContainingOrGivenNameContainingOrSurnameContaining(
            final String identifier, final String givenName, final String surname, final Pageable pageable);

    Optional<CustomerEntity> findByIdentifier(final String identifier);

    Page<CustomerEntity> findByCurrentStateNot(final String state, final Pageable pageable);

    Page<CustomerEntity> findByCurrentStateNotAndIdentifierContainingOrGivenNameContainingOrSurnameContaining(
            final String state, final String identifier, final String givenName, final String surname, final Pageable pageable);
}