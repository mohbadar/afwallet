package af.gov.anar.ebreshna.configuration.network.dtr_category;

import af.gov.anar.ebreshna.configuration.network.dtr_category.DtrCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DtrCategoryRepository extends JpaRepository<DtrCategory, Long> {
}
