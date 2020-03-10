package af.gov.anar.ebreshna.csc.complaint_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRequestRepository extends JpaRepository<ComplaintRequest, Long>, RevisionRepository<ComplaintRequest, Long, Integer> {
}
