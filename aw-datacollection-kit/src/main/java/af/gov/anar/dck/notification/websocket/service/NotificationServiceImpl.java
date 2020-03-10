package af.gov.anar.dck.notification.websocket.service;

import af.gov.anar.dck.notification.websocket.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for sending notification messages.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

      // The SimpMessagingTemplate is used to send Stomp over WebSocket messages.
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void emitNotification(Notification notification) {
        template.convertAndSend("/notifications", notification);
    }


  /**
   * Send notification to users subscribed on channel "/user/queue/notify".
   *
   * The message will be sent only to the user with the given username.
   * 
   * @param notification The notification message.
   * @param username The username for the user to send notification.
   */
  @Override
  public void notify(Notification notification, String username) {
    template.convertAndSendToUser(
      username, 
      "/queue/notify", 
      notification
    );
    return;
  }

}