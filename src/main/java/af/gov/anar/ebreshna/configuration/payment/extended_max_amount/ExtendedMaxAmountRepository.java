package af.gov.anar.ebreshna.configuration.payment.extended_max_amount;

import af.gov.anar.ebreshna.configuration.payment.extended_max_amount.ExtendedMaxAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtendedMaxAmountRepository extends JpaRepository<ExtendedMaxAmount, Long> {
}
