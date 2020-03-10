package af.gov.anar.ebreshna.configuration.payment.thirdparty_payment;

import af.gov.anar.ebreshna.configuration.payment.thirdparty_payment.ThirdPartyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyPaymentRepository extends JpaRepository<ThirdPartyPayment, Long> {
}
