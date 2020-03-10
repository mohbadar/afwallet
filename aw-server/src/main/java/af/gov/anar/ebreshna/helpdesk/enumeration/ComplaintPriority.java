package af.gov.anar.ebreshna.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ComplaintPriority {

    URGENT ("URGENT"),
    HIGH ("HIGH"),
    MEDIUM ("MEDIUM"),
    LOW("LOW");

    private String content;
}
