package af.gov.anar.ebreshna.configuration.billing.process;

import af.gov.anar.ebreshna.configuration.billing.process.ProcessConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessConfiguration, Long> {
}
