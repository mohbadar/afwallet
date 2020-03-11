package af.gov.anar.ebreshna.configuration.csc.approval_limit;

import af.gov.anar.ebreshna.configuration.csc.approval_limit.ApprovalLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalLimitRepository extends JpaRepository<ApprovalLimit, Long> {
}
