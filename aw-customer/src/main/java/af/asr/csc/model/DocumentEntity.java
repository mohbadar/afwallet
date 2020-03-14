package af.asr.csc.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import af.asr.csc.model.CustomerEntity;
import af.asr.infrastructure.revision.AuditEnabledEntity;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class DocumentEntity extends AuditEnabledEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CustomerEntity customer;

    @Column(name = "identifier", nullable = false)
    private String identifier;

    @SuppressWarnings("DefaultAnnotationParam")
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "is_completed", nullable = false)
    private Boolean completed;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;

}