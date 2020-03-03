package af.gov.anar.ebreshna.configuration.csc.approval_limit;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.office.designation.DesignationMaster;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "csc_approval_limit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ApprovalLimit extends BaseEntity {

//    @ManyToOne(targetEntity = ApprovalLimit.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = true, name = "approval_limit_id")
//    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//    private ApprovalLimit approvalLimit;

    @ManyToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @ManyToOne(targetEntity = DesignationMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "designation_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DesignationMaster designationMaster;

    private BigDecimal amount;
}
