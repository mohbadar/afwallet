package af.gov.anar.ebreshna.configuration.payment.posting_days;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.payment.branch.BankBranch;
import af.gov.anar.ebreshna.configuration.payment.bank.BankMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "payment_posting_days_config", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class PostingDaysConfiguration extends BaseEntity {


    @OneToOne(targetEntity = BankBranch.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "bank_branch_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BankBranch bankBranch;


    @OneToOne(targetEntity = BankMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "bank_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BankMaster bankMaster;

    @Column
    private int daysConfigured;
}