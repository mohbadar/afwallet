package af.gov.anar.ebreshna.configuration.metering.office_cycle_relation;

import af.gov.anar.ebreshna.configuration.metering.office_cycle_relation.OfficeCycleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeCycleRepository extends JpaRepository<OfficeCycleRelation, Long> {
}
