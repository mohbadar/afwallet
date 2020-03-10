package af.gov.anar.ebreshna.configuration.network.distributed_transformer;

import af.gov.anar.ebreshna.configuration.network.distributed_transformer.DistributionTransformerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionTransformerMasterRepository extends JpaRepository<DistributionTransformerMaster, Long> {
}
