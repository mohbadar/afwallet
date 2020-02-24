package af.gov.anar.ebreshna.configuration.network.repository;

import af.gov.anar.ebreshna.configuration.network.model.StationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationTypeRepository extends JpaRepository<StationType, Long> {
}
