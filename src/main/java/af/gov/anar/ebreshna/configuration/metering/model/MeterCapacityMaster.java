package af.gov.anar.ebreshna.configuration.metering.model;

import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffChargeMaximumType;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "metering_meter_capacity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class MeterCapacityMaster extends BaseEntity {

    @Column
    private double minCapacity;

    @Column
    private double maxCapacity;

    @Column
    private TariffChargeMaximumType tariffChargeMaximumType;

    @Column
    private String remark;
}
