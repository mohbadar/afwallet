package af.gov.anar.ebreshna.helpdesk.repository;

import af.gov.anar.ebreshna.helpdesk.model.ComplaintType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintTypeRepository extends JpaRepository<ComplaintType,Long> {
}
