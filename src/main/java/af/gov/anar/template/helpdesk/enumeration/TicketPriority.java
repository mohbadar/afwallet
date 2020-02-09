package af.gov.anar.template.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketPriority {

    URGENT ("URGENT"),
    HIGH ("HIGH"),
    MEDIUM ("MEDIUM"),
    LOW("LOW");

    private String content;
}
