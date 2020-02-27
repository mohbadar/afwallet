package af.gov.anar.ebreshna.nsc.applicant_document;

import af.gov.anar.ebreshna.nsc.applicant_document.ApplicantDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantDocumentRepository extends JpaRepository<ApplicantDocument, Long> {
}
