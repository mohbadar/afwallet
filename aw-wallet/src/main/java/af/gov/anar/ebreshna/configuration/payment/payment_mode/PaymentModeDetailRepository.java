package af.gov.anar.ebreshna.configuration.payment.payment_mode;

import af.gov.anar.ebreshna.configuration.payment.payment_mode.PaymentModeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentModeDetailRepository extends JpaRepository<PaymentModeDetail, Long> {
}
