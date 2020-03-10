package af.gov.anar.ebreshna.configuration.office.premises_sub_category;

import af.gov.anar.ebreshna.configuration.office.premises_sub_category.PremisesSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremisesSubCategoryRepository extends JpaRepository<PremisesSubCategory, Long> {
}