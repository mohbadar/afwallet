package af.gov.anar.template.helpdesk.model;

import af.gov.anar.template.helpdesk.enumeration.TicketPriority;
import af.gov.anar.template.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_type", schema = Schema.HELP_DESK_DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class TicketType {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private TicketPriority ticketPriority;

}
