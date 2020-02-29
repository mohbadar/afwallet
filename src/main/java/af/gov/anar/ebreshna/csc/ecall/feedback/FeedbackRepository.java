package af.gov.anar.ebreshna.csc.ecall.feedback;

import af.gov.anar.ebreshna.csc.complaint.complaint_modification.ComplaintModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>, RevisionRepository<Feedback, Long, Integer> {
}
