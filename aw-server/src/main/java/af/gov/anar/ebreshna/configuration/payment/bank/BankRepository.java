package af.gov.anar.ebreshna.configuration.payment.bank;

import af.gov.anar.ebreshna.configuration.payment.bank.BankMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankMaster, Long> {
}
