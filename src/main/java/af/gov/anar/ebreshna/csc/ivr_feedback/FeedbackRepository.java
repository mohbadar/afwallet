package af.gov.anar.ebreshna.csc.ivr_feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>, RevisionRepository<Feedback, Long, Integer> {
}
