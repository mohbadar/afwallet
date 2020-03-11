package af.gov.anar.ebreshna.configuration.billing.tariff_charge_detail;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.tariff_charge_detail.TariffChargeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffChargeDetailRepository extends JpaRepository<TariffChargeDetail, Long> , RevisionRepository<TariffChargeDetail, Long, Integer> {
}
