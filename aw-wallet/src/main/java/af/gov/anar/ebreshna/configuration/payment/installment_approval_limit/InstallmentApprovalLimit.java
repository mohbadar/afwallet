package af.gov.anar.ebreshna.configuration.payment.installment_approval_limit;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import af.gov.anar.ebreshna.configuration.office.designation.DesignationMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_installment_approval_limit", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class InstallmentApprovalLimit extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = TariffCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "tariff_category_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCategory tariffCategory;


    @OneToOne(targetEntity = DesignationMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "designation_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DesignationMaster designationMaster;

    @Column(nullable = false)
    private int numberOfInstallment;

    @Column
    private BigDecimal amount;

    @Column
    private int installmentGraceDays;
}
