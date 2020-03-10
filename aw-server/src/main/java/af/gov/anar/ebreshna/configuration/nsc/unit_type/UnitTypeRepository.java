package af.gov.anar.ebreshna.configuration.nsc.unit_type;

import af.gov.anar.ebreshna.configuration.nsc.unit_type.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType, Long> {
}
