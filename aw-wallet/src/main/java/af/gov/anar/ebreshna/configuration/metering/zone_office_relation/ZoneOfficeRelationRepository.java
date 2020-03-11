package af.gov.anar.ebreshna.configuration.metering.zone_office_relation;

import af.gov.anar.ebreshna.configuration.metering.zone_office_relation.ZoneOfficeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneOfficeRelationRepository extends JpaRepository<ZoneOfficeRelation, Long> {
}
