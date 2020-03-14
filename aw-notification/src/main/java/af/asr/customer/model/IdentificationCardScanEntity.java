package af.asr.customer.model;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "identification_card_scan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class IdentificationCardScanEntity extends AuditEnabledEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Column(name = "size")
    private Long size;
    @Column(name = "content_type")
    private String contentType;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "identification_card_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private IdentificationCardEntity identificationCard;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;



}
