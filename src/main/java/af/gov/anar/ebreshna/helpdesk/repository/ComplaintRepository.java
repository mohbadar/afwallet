package af.gov.anar.ebreshna.helpdesk.repository;

import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
