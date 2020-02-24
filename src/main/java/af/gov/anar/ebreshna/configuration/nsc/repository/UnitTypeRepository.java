package af.gov.anar.ebreshna.configuration.nsc.repository;

import af.gov.anar.ebreshna.configuration.nsc.model.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType, Long> {
}
