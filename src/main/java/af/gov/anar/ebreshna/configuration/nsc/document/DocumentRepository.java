package af.gov.anar.ebreshna.configuration.nsc.document;

import af.gov.anar.ebreshna.configuration.nsc.document.DocumentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentMaster, Long> {
}
