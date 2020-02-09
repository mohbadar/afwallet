package af.gov.anar.ebreshna.helpdesk.repository;

import af.gov.anar.ebreshna.helpdesk.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
