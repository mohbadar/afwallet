package af.gov.anar.ebreshna.configuration.metering.consumption_percentage;

import af.gov.anar.ebreshna.configuration.billing.tariff_charge.TariffCharge;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.metering.cycle.CycleConfiguration;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "metering_consumption_percentage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ConsumptionPercentage extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = TariffCharge.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "tariff_charge_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCharge tariffCharge;


    @Column
    private double chargePercentage;

    @OneToOne(targetEntity = CycleConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "cycle_configuration_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CycleConfiguration cycleConfiguration;

    @Column
    private int year;



}
