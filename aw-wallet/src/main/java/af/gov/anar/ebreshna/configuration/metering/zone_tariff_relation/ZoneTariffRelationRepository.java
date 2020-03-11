package af.gov.anar.ebreshna.configuration.metering.zone_tariff_relation;

import af.gov.anar.ebreshna.configuration.metering.zone_tariff_relation.ZoneTariffRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneTariffRelationRepository extends JpaRepository<ZoneTariffRelation, Long> {
}
