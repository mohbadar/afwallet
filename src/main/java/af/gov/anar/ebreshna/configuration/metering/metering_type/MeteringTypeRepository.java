package af.gov.anar.ebreshna.configuration.metering.metering_type;

import af.gov.anar.ebreshna.configuration.metering.metering_type.MeteringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeteringTypeRepository extends JpaRepository<MeteringType, Long> {
}
