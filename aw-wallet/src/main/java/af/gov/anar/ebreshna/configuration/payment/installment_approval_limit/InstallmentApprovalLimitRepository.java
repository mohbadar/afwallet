package af.gov.anar.ebreshna.configuration.payment.installment_approval_limit;

import af.gov.anar.ebreshna.configuration.payment.installment_approval_limit.InstallmentApprovalLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentApprovalLimitRepository extends JpaRepository<InstallmentApprovalLimit, Long> {
}
