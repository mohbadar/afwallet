package af.gov.anar.ebreshna.common.network.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.model.OfficeMaster;
import af.gov.anar.ebreshna.common.province.Province;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "network_meter_box_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class MeterReaderMaster extends BaseEntity {

    @ManyToOne(targetEntity = Province.class)
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class)
    private OfficeMaster officeMaster;

    @Column
    private String meterReaderName;

    @Column
    private String meterReaderNumber;

    @Column
    private String meterReaderAddress;

    @Column
    private String meterReaderPhoneNumber;
}
