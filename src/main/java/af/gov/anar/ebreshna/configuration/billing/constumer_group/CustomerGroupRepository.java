package af.gov.anar.ebreshna.configuration.billing.constumer_group;

import af.gov.anar.ebreshna.configuration.billing.constumer_group.CustomerGroupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroupMaster, Long> {
}
