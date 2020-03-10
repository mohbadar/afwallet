package af.gov.anar.ebreshna.configuration.metering.activity_relation;

import af.gov.anar.ebreshna.configuration.metering.activity_relation.ActivityRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRelationRepository extends JpaRepository<ActivityRelation, Long> {
}
