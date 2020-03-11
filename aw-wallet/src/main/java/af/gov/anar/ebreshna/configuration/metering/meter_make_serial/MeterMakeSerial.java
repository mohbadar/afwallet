package af.gov.anar.ebreshna.configuration.metering.meter_make_serial;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "metering_meter_make_serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class MeterMakeSerial extends BaseEntity {

    @Column
    private String meterMake;

    @Column
    private String phase;

    @Column
    private double fromRange;

    @Column
    private double toRange;
}
