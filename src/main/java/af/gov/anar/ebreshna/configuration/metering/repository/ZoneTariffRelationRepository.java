package af.gov.anar.ebreshna.configuration.metering.repository;

import af.gov.anar.ebreshna.configuration.metering.model.ActivityRelation;
import af.gov.anar.ebreshna.configuration.metering.model.ZoneTariffRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneTariffRelationRepository extends JpaRepository<ZoneTariffRelation, Long> {
}
