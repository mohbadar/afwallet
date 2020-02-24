package af.gov.anar.ebreshna.configuration.common.fee.repository;

import af.gov.anar.ebreshna.configuration.common.fee.model.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface FeeTypeRepository extends JpaRepository<FeeType, Long> {
}
