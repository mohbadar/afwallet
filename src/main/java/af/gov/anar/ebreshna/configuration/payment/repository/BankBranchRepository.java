package af.gov.anar.ebreshna.configuration.payment.repository;

import af.gov.anar.ebreshna.configuration.payment.model.BankBranch;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {
}
