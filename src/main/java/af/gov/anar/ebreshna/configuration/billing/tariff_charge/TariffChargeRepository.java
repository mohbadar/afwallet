package af.gov.anar.ebreshna.configuration.billing.tariff_charge;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.tariff_charge.TariffCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffChargeRepository extends JpaRepository<TariffCharge, Long>, RevisionRepository<TariffCharge, Long, Integer> {
}
