package af.gov.anar.ebreshna.configuration.nsc.repository;

import af.gov.anar.ebreshna.configuration.nsc.model.DocumentCategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentCategoryRepository extends JpaRepository<DocumentCategoryMaster, Long> {
}
