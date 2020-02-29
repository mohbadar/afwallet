package af.gov.anar.ebreshna.csc.complaint.complaint_request_type;

import af.gov.anar.ebreshna.csc.complaint.complaint_modification.ComplaintModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRequestTypeRepository extends JpaRepository<ComplaintRequestType, Long>, RevisionRepository<ComplaintRequestType, Long, Integer> {
}
