package af.gov.anar.ebreshna.configuration.office.premises_nature;

import af.gov.anar.ebreshna.configuration.office.premises_nature.PremisesNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremisesNatureRepository extends JpaRepository<PremisesNature, Long> {
}
