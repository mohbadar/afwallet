package af.gov.anar.ebreshna.nsc.repository;

import af.gov.anar.ebreshna.nsc.model.ApplicantDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantDocumentRepository extends JpaRepository<ApplicantDocument, Long> {
}
