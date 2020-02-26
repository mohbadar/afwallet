package af.gov.anar.ebreshna.configuration.payment.branch;

import af.gov.anar.ebreshna.configuration.payment.branch.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {
}
