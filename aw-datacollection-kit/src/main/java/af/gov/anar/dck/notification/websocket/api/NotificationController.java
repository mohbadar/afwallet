package af.gov.anar.dck.notification.websocket.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping(value = "/send/message")
    public void onRecievedMessage(String message)
    {
        log.info("RECEIVED SOCKET MESSAGE: => "+ message);
        this.template.convertAndSend("/notifications", message);
    }

}