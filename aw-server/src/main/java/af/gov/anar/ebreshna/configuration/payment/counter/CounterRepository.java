package af.gov.anar.ebreshna.configuration.payment.counter;

import af.gov.anar.ebreshna.configuration.payment.counter.CounterMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<CounterMaster, Long> {
}
