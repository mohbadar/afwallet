package af.gov.anar.ebreshna.configuration.billing.tariff_parameter;

import af.gov.anar.ebreshna.configuration.billing.tariff_parameter.TariffParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffParameterRepository extends JpaRepository<TariffParameter, Long> {
}
