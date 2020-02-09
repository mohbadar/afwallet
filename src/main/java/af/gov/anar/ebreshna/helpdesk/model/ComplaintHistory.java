package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.common.model.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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

    /**
     *   action: { type: String, required: true },
     *   date: { type: Date, default: Date.now, required: true },
     *   owner: { type: mongoose.Schema.Types.ObjectId, ref: 'accounts' },
     *   description: { type: String }
     */
    @Column(nullable = false)
    private String action;
    @Column
    private Date date;
    @Column(nullable = false)
    private String desciption;

    @PrePersist
    public void setDate()
    {
        date = new Date();
    }
}
