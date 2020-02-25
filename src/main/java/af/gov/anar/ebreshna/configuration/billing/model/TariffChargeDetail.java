package af.gov.anar.ebreshna.configuration.billing.model;

import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffChargeCurrency;
import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffChargeMaximumType;
import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffChargePeriod;
import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffChargeType;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.metering.model.CycleConfiguration;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "billing_tariff_charge_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class TariffChargeDetail extends BaseEntity {

    @ManyToOne(targetEntity = TariffCharge.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "tariff_charge_id")
    private TariffCharge tariffCharge;

    @Column
    private double minimum;

    @Column
    private double maximum;

    @Column
    private double rebate;

    @Column
    private double rate;

    @Column
    private TariffChargeType tariffChargeType;

    @Column
    private TariffChargeMaximumType tariffChargeMaximumType;

    @Column
    private TariffChargePeriod tariffChargePeriod;

    @Column
    private TariffChargeCurrency tariffChargeCurrency;

}
