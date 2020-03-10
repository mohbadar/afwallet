package af.gov.anar.ebreshna.configuration.billing.tariff_parameter;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.tariff_parameter.TariffParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffParameterRepository extends JpaRepository<TariffParameter, Long>, RevisionRepository<TariffParameter, Long, Integer> {
}
