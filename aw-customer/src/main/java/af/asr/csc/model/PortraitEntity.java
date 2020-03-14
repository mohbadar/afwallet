package af.asr.csc.model;

import af.asr.csc.model.CustomerEntity;
import af.asr.infrastructure.revision.AuditEnabledEntity;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "portrait")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class PortraitEntity extends AuditEnabledEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CustomerEntity customer;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "size")
    private Long size;

    @Column(name = "content_type")
    private String contentType;

}