package af.gov.anar.ebreshna.helpdesk.repository;

import af.gov.anar.ebreshna.helpdesk.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}