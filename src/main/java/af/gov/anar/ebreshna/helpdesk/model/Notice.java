package af.gov.anar.ebreshna.helpdesk.model;


import af.gov.anar.ebreshna.common.model.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "notice", schema = Schema.HELP_DESK_DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Notice extends BaseEntity {

    /**
     *   name: { type: String, required: true },
     *   date: { type: Date, default: Date.now, required: true },
     *   message: { type: String, required: true },
     *   active: { type: Boolean, default: false, required: true },
     *   activeDate: { type: Date, default: Date.now },
     *   alertWindow: { type: Boolean, default: false }
     */

    private String name;
    private Date date;
    private String message;
    private boolean active;
    private Date activeDate;
    private boolean alertWindow;


}
