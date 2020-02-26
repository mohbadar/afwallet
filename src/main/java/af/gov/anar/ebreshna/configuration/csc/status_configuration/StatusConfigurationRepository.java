package af.gov.anar.ebreshna.configuration.csc.status_configuration;

import af.gov.anar.ebreshna.configuration.csc.status_configuration.StatusConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusConfigurationRepository extends JpaRepository<StatusConfiguration, Long> {
}
