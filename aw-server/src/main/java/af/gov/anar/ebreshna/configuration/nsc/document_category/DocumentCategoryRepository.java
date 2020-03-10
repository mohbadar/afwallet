package af.gov.anar.ebreshna.configuration.nsc.document_category;

import af.gov.anar.ebreshna.configuration.nsc.document_category.DocumentCategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentCategoryRepository extends JpaRepository<DocumentCategoryMaster, Long> {
}
