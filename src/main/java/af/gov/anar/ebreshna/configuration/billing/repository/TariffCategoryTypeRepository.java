package af.gov.anar.ebreshna.configuration.billing.repository;

import af.gov.anar.ebreshna.configuration.billing.model.TariffCategoryTypeMaster;
import af.gov.anar.ebreshna.configuration.billing.model.TrLossesConfiguration;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffCategoryTypeRepository extends JpaRepository<TariffCategoryTypeMaster, Long> {
}
