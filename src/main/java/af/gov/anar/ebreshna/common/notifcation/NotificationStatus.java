package af.gov.anar.ebreshna.common.notifcation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  NotificationStatus {

    READ("READ"),
    UNREAD("UNREAD");

    private String content;
}