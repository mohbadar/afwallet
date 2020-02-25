package af.gov.anar.ebreshna.configuration.billing.model;

import af.gov.anar.ebreshna.configuration.billing.enumeration.VoltageGroup;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "billing_voltage_group_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class VoltageGroupMaster extends BaseEntity {

    @ManyToOne(targetEntity = VoltageUnit.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "voltage_unit_id")
    private VoltageUnit voltageUnit;

    @Column
    private String voltageValue;

    @Column
    private VoltageGroup voltageGroup;

//    @ManyToOne(targetEntity = Phase.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "tariff_charge_id")
//    private TariffCharge tariffCharge;

    @Column
    private String phase;

    @Column
    private String voltageDescription;
}
