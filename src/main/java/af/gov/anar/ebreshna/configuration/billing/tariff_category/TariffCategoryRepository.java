package af.gov.anar.ebreshna.configuration.billing.tariff_category;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.tariff_category.TariffCategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffCategoryRepository extends JpaRepository<TariffCategoryMaster, Long>, RevisionRepository<TariffCategoryMaster, Long, Integer> {
}
