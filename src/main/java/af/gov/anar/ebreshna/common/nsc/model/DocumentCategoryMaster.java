package af.gov.anar.ebreshna.common.nsc.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.network.model.DtrCategory;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "nsc_document_category_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class DocumentCategoryMaster extends BaseEntity {

    @ManyToOne(targetEntity = DtrCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dtr_category_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DtrCategory dtrCategory;


    @ManyToOne(targetEntity = DocumentMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DocumentMaster documentMaster;

    @Column(nullable = false)
    private int numberOfCopies;
}
