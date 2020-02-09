package af.gov.anar.ebreshna.common.notifcation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    public List<Notification> findByNotificationStatus(NotificationStatus status);
}
