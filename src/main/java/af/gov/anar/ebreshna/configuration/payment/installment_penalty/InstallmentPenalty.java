package af.gov.anar.ebreshna.configuration.payment.installment_penalty;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import af.gov.anar.ebreshna.configuration.metering.cycle.CycleConfiguration;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "payment_installment_panelty", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class InstallmentPenalty extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;


    @OneToOne(targetEntity = CycleConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "cycle_configuration_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CycleConfiguration cycleConfiguration;

    @Column
    private double penaltyPrecentage;

    @OneToOne(targetEntity = TariffCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "tariff_category_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCategory tariffCategory;

    @Column
    private int year;

}
