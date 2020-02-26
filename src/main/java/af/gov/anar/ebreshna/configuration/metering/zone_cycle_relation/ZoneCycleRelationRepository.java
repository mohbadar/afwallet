package af.gov.anar.ebreshna.configuration.metering.zone_cycle_relation;

import af.gov.anar.ebreshna.configuration.metering.zone_cycle_relation.ZoneCycleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneCycleRelationRepository extends JpaRepository<ZoneCycleRelation, Long> {
}
