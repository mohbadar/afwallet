package af.gov.anar.ebreshna.configuration.nsc.unit;

import af.gov.anar.ebreshna.configuration.nsc.unit.UnitMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitMaster, Long> {
}
