package af.gov.anar.ebreshna.configuration.metering.consumption_percentage;

import af.gov.anar.ebreshna.configuration.metering.consumption_percentage.ConsumptionPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionPercentageRepository extends JpaRepository<ConsumptionPercentage, Long> {
}
