package af.gov.anar.ebreshna.configuration.billing.tr_loss;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "billing_tr_losses_configuration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class TrLossesConfiguration extends BaseEntity {

    @ManyToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "applicant_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Applicant applicant;


}
