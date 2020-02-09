package af.gov.anar.template.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketPriority {

    CRITICAL ("CRITICAL"),
    IMPORTANT ("IMPORTANT"),
    NORMAL ("NORMAL"),
    LOW("LOW");

    private String content;
}
