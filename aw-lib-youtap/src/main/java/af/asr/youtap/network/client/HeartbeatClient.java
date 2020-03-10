package af.asr.youtap.network.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Slf4j
public class HeartbeatClient {
    private final MessageChannel outboudChannel;
    private final PollableChannel inboundChannel;

    public HeartbeatClient(MessageChannel outboudChannel, PollableChannel inboundChannel) {
        this.inboundChannel = inboundChannel;
        this.outboudChannel = outboudChannel;
    }

    @EventListener
    public void initializaAfterContextIsReady(ContextRefreshedEvent event) {
        log.info("Starting Heartbeat client...");
        start();
    }

    public void start() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            while (true) {
                try {
                    log.info("Sending Heartbeat");
                    outboudChannel.send(new GenericMessage<>("status"));
                    Message<?> message = inboundChannel.receive(1000);
                    if (message == null) {
                        log.error("Heartbeat timeouted");
                    } else {
                        String messageStr = new String((byte[]) message.getPayload());
                        if (messageStr.equals("OK")) {
                            log.info("Heartbeat OK response received");
                        } else {
                            log.error("Unexpected message content from server: " + messageStr);
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }, 0, 10000, TimeUnit.SECONDS);
    }
}
