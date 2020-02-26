package af.gov.anar.ebreshna.configuration.billing.tariff_category_type;

import af.gov.anar.ebreshna.configuration.billing.tariff_category_type.TariffCategoryTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffCategoryTypeRepository extends JpaRepository<TariffCategoryTypeMaster, Long> {
}
