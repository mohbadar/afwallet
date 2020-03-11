package af.gov.anar.ebreshna.configuration.common.workflowdata;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.lib.workflow.model.Workflow;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "workflow_transition_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class WorkflowTransitionData extends BaseEntity {

    @ManyToOne(targetEntity = Workflow.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "workflow_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Workflow workflow;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String transitionName;

    @Column(nullable = false)
    private String content;
}
