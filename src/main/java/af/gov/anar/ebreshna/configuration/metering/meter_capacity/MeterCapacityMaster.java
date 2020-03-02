package af.gov.anar.ebreshna.configuration.metering.meter_capacity;

import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffChargeMaximumType;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.datatype.StringToEnumFactory;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

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

//    @Column
//    @Enumerated(EnumType.STRING)
//    @Convert(converter = StringToEnumFactory.class)
//    private TariffChargeMaximumType tariffChargeMaximumType;

    @Column
    private String tariffChargeMaximumType;

    @Column
    private String remark;
}
