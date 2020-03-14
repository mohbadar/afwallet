package af.asr.customer.document.repository;

import af.asr.customer.document.model.DocumentPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface DocumentPageRepository extends JpaRepository<DocumentPageEntity, Long> {
    @Query("SELECT d FROM DocumentPageEntity d WHERE d.document.customer.identifier = :customerIdentifier AND d.document.identifier = :documentIdentifier AND d.pageNumber = :pageNumber")
    Optional<DocumentPageEntity> findByCustomerIdAndDocumentIdentifierAndPageNumber(
            @Param("customerIdentifier") String customerIdentifier, @Param("documentIdentifier") String documentIdentifier, @Param("pageNumber") Integer pageNumber);

    @Query("SELECT d FROM DocumentPageEntity d WHERE d.document.customer.identifier = :customerIdentifier AND d.document.identifier = :documentIdentifier")
    Stream<DocumentPageEntity> findByCustomerIdAndDocumentIdentifier(
            @Param("customerIdentifier") String customerIdentifier, @Param("documentIdentifier") String documentIdentifier);
}