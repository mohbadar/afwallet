package af.gov.anar.ebreshna.configuration.nsc.unit;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.nsc.appliance.ApplianceMaster;
import af.gov.anar.ebreshna.configuration.nsc.metric.MetricMaster;
import af.gov.anar.ebreshna.configuration.nsc.supply_voltage.SupplyVoltage;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "nsc_unit_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class UnitMaster extends BaseEntity {

    @ManyToOne(targetEntity = ApplianceMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "appliance_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private ApplianceMaster applianceMaster;

    @ManyToOne(targetEntity = SupplyVoltage.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "supply_voltage_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private SupplyVoltage supplyVoltage;

    @ManyToOne(targetEntity = MetricMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "metric_master_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MetricMaster unitType;

    @Column
    private String ratingValue;

    @Column
    private double consumedUnitsWatts;

    @Column
    private double consumedUnitsAmps;
}
