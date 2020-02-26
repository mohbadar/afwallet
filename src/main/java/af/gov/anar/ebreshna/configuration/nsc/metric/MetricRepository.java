package af.gov.anar.ebreshna.configuration.nsc.metric;

import af.gov.anar.ebreshna.configuration.nsc.metric.MetricMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<MetricMaster, Long> {
}
