package af.gov.anar.ebreshna.configuration.billing.bill_due_day;

import af.gov.anar.ebreshna.configuration.billing.bill_due_day.BillDueDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDueDayRepository extends JpaRepository<BillDueDay, Long> {
}
