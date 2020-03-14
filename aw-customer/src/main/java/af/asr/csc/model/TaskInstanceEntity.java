package af.asr.csc.model;

import af.asr.infrastructure.revision.AuditEnabledEntity;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task_instance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class TaskInstanceEntity extends AuditEnabledEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "task_definition_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TaskDefinitionEntity taskDefinition;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CustomerEntity customer;
    @Column(name = "a_comment")
    private String comment;
    @Column(name = "executed_on")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime executedOn;
    @Column(name = "executed_by")
    private String executedBy;

}