package af.gov.anar.ebreshna.csc.complaint.complaint_modification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintModificationRepository extends JpaRepository<ComplaintModification, Long>, RevisionRepository<ComplaintModification, Long, Integer> {
}
