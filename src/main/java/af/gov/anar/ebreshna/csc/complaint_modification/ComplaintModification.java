package af.gov.anar.ebreshna.csc.complaint_modification;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.csc.complaint_request.ComplaintRequest;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "csc_complaint_modifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ComplaintModification extends BaseEntity {

    @ManyToOne(targetEntity = ComplaintRequest.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "complaint_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private ComplaintRequest complaint;

    @Column
    private String action;

    @Column
    private String oldContent;

    @Column
    private String currentContent;


}
