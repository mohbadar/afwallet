package af.gov.anar.ebreshna.configuration.office.premises_category;

import af.gov.anar.ebreshna.configuration.office.premises_category.PremisesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremisesCategoryRepository extends JpaRepository<PremisesCategory, Long> {
}
