package af.gov.anar.ebreshna.configuration.payment.extended_max_amount;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.payment.branch.BankBranch;
import af.gov.anar.ebreshna.configuration.payment.bank.BankMaster;
import af.gov.anar.ebreshna.configuration.payment.counter.CounterMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_extended_max_amount", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ExtendedMaxAmount extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = BankMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "bank_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BankMaster bankMaster;

    @OneToOne(targetEntity = BankBranch.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "bank_branch_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BankBranch bankBranch;

    @OneToOne(targetEntity = CounterMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "counter_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CounterMaster counterMaster;

    @Column
    private BigDecimal dialyMaxAmount;

    @Column
    private BigDecimal weeklyMaxAmount;

    @Column
    private BigDecimal monthlyMaxAmount;

    @Column
    private BigDecimal amountPaid;

    @Column
    private  BigDecimal weeklyPaymentAmount;

    @Column
    private  BigDecimal monthlyPaymentAmount;

    @Column
    private BigDecimal extendedMaxAmount;
}
