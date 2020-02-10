package af.gov.anar.ebreshna.customerservice.repository;

import af.gov.anar.ebreshna.customerservice.model.ApprovalLimit;
import af.gov.anar.ebreshna.customerservice.model.StatusConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusConfigurationRepository extends JpaRepository<StatusConfiguration, Long> {
}
