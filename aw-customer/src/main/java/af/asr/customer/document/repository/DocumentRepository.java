package af.asr.customer.document.repository;

import af.asr.customer.document.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    @Query("SELECT d FROM DocumentEntity d WHERE d.customer.identifier = :customerIdentifier AND d.identifier = :documentIdentifier")
    Optional<DocumentEntity> findByCustomerIdAndDocumentIdentifier(
            @Param("customerIdentifier") String customerIdentifier, @Param("documentIdentifier") String documentIdentifier);

    @Query("SELECT d FROM DocumentEntity d WHERE d.customer.identifier = :customerIdentifier")
    Stream<DocumentEntity> findByCustomerId(
            @Param("customerIdentifier") String customerIdentifier);
}