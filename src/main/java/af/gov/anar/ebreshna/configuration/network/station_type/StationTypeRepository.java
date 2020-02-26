package af.gov.anar.ebreshna.configuration.network.station_type;

import af.gov.anar.ebreshna.configuration.network.station_type.StationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationTypeRepository extends JpaRepository<StationType, Long> {
}
