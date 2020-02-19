package af.gov.anar.ebreshna.common.base.notifcation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  NotificationType {

    GREEN_CHECK(0),
    WARNING(1),
    RED_EXCLAMATION(2);

    private int content;
}
