package af.gov.anar.ebreshna.configuration.billing.consumer_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroupMaster, Long>, RevisionRepository<CustomerGroupMaster, Long, Integer> {
}