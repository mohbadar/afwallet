package af.gov.anar.ebreshna.nsc.applicant_document;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.nsc.document.DocumentMaster;
import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "nsc_applicant_document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ApplicantDocument extends BaseEntity {

    @ManyToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "applicant_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Applicant applicant;

    @ManyToOne(targetEntity = DocumentMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "document_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DocumentMaster documentMaster;

    @Column
    private String type;

    @Column
    private String filePath;
}
