package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.common.model.BaseEntity;
import af.gov.anar.ebreshna.helpdesk.enumeration.NotificationType;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "notification", schema = Schema.HELP_DESK_DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Notification extends BaseEntity {

    /**
     * created: { type: Date, default: Date.now },
     *   owner: { type: mongoose.Schema.Types.ObjectId, ref: 'accounts' },
     *   title: { type: String, required: true },
     *   message: { type: String, required: true },
     *   type: Number,
     *   data: Object,
     *   unread: { type: Boolean, default: true }
     */
    private String title;
    private String message;
    private NotificationType notificationType;
    @Lob
    @Column
    private byte[] data;
    private boolean unread =true;
}
