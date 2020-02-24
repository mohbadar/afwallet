package af.gov.anar.ebreshna.configuration.payment.repository;

import af.gov.anar.ebreshna.configuration.payment.model.BankBranch;
import af.gov.anar.ebreshna.configuration.payment.model.InstallmentApprovalLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentApprovalLimitRepository extends JpaRepository<InstallmentApprovalLimit, Long> {
}
