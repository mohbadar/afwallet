package af.gov.anar.ebreshna.customerservice.repository;

import af.gov.anar.ebreshna.customerservice.model.ApprovalLimit;
import af.gov.anar.ebreshna.customerservice.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
