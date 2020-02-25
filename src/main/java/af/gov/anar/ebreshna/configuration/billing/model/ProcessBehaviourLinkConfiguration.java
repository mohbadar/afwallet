package af.gov.anar.ebreshna.configuration.billing.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.csc.model.RequestType;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "billing_process_behaviour_link_configuration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ProcessBehaviourLinkConfiguration extends BaseEntity {

    @ManyToOne(targetEntity = ProcessConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "process_configuration_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private ProcessConfiguration processConfiguration;


    @ManyToOne(targetEntity = BehaviourConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "behaviour_configuration_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BehaviourConfiguration behaviourConfiguration;
}
