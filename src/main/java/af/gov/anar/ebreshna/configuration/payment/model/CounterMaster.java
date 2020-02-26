package af.gov.anar.ebreshna.configuration.payment.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "payment_counter_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class CounterMaster extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = BankBranch.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "bank_branch_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BankBranch bankBranch;

    @Column
    private String counterName;

    @Column
    private String counterCode;

    @Column
    private boolean maxAmountRequired;
}
