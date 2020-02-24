package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket_history", schema = Schema.HELP_DESK_DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ComplaintHistory extends BaseEntity {


    @Column(nullable = false)
    private String action;
    @Column
    private Date date;
    @Column(nullable = false)
    private String desciption;

    @ManyToOne(targetEntity = Complaint.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    @JsonIgnore
    private Complaint complaint;

    @PrePersist
    public void setDate()
    {
        date = new Date();
    }
}
