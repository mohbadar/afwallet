package af.gov.anar.dck.notification.websocket.service;

import af.gov.anar.dck.notification.websocket.dto.Notification;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

   public  void emitNotification(Notification notification);

   public void notify(Notification notification, String username);
}