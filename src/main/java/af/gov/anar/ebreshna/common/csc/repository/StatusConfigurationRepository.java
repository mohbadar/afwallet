package af.gov.anar.ebreshna.common.csc.repository;

import af.gov.anar.ebreshna.common.csc.model.StatusConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusConfigurationRepository extends JpaRepository<StatusConfiguration, Long> {
}
