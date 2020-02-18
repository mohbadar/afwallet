package af.gov.anar.ebreshna.common.network.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.network.enumeration.MeterBoxModeType;
import af.gov.anar.ebreshna.common.network.enumeration.MeterBoxType;
import af.gov.anar.ebreshna.common.network.enumeration.TypeOfMounting;
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
public class MeterBoxMaster extends BaseEntity {

    @ManyToOne(targetEntity = Province.class)
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class)
    private OfficeMaster officeMaster;

    @ManyToOne(targetEntity = AreaMaster.class)
    private AreaMaster areaMaster;

    @ManyToOne(targetEntity = MeterReaderMaster.class)
    private MeterReaderMaster meterReaderMaster;

    @ManyToOne(targetEntity = StationMaster.class)
    private DistributionTransformerMaster distributionTransformerMaster;

    @ManyToOne(targetEntity = MeterBoxMaster.class)
    private MeterBoxMaster neighbourMeterBoxMaster;

    @Column
    private TypeOfMounting typeOfMounting;
    @Column
    private MeterBoxType meterBoxType;

    @Column
    private MeterBoxModeType meterBoxModeType;

    @Column
    private String meterBoxNumber;

    @Column
    private String daySequence;

    @Column
    private String weekSequence;

    @Column
    private String thirdPhaseCount;
    @Column
    private String singlePhaseCount;


}