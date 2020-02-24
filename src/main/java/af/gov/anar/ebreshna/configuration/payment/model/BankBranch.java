package af.gov.anar.ebreshna.configuration.payment.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.common.workingcalender.model.WorkingCalenderTemplate;
import af.gov.anar.ebreshna.configuration.office.model.OfficeMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "payment_bank_branch", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class BankBranch extends BaseEntity {


    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = BankMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "bank_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BankMaster bankMaster;


    @Column
    private String address;

    @Column
    private String branchCode;

    @Column
    private String branchName;

    @OneToOne(targetEntity = WorkingCalenderTemplate.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "working_calender_template_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private WorkingCalenderTemplate workingCalenderTemplate;
}
