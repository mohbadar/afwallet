package af.gov.anar.dck.notification.websocket.dto;

import af.gov.anar.dck.infrastructure.util.enumeration.InstanceHistoryStatus;
import lombok.*;

/**
 * Data Transfor Object for Notification 
 * 
 * 
 * The Transfer Object pattern is used when we want to pass data with multiple attributes in one shot from client to server or server to client.
 * Transfer object is also known as Value Object. Transfer Object is a simple POJO class having getter/setter methods and is serializable so that it can be transferred over the network.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private long instanceHistoryId;
    private long instanceId;
    private String message; 
    private String description;
    private InstanceHistoryStatus instanceHistoryStatus;

}