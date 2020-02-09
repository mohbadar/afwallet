package af.gov.anar.template.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketStatus {

    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    INVALID("INVALID"),
    SUSPENDED("SUSPENDED"),
    PENDING("PENDING");

    private String content;
}