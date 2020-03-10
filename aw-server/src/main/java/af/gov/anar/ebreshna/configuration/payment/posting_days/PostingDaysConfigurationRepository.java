package af.gov.anar.ebreshna.configuration.payment.posting_days;

import af.gov.anar.ebreshna.configuration.payment.posting_days.PostingDaysConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingDaysConfigurationRepository extends JpaRepository<PostingDaysConfiguration, Long> {
}
