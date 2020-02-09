package af.gov.anar.ebreshna.helpdesk.repository;

import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.helpdesk.model.ComplaintHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintHistoryRepository extends JpaRepository<ComplaintHistory, Long> {
}
