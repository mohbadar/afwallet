package af.gov.anar.ebreshna.configuration.network.meter_box;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.area.AreaMaster;
import af.gov.anar.ebreshna.configuration.network.distributed_transformer.DistributionTransformerMaster;
import af.gov.anar.ebreshna.configuration.network.enumeration.MeterBoxModeType;
import af.gov.anar.ebreshna.configuration.network.enumeration.MeterBoxType;
import af.gov.anar.ebreshna.configuration.network.enumeration.TypeOfMounting;
import af.gov.anar.ebreshna.configuration.network.meter_reader.MeterReaderMaster;
import af.gov.anar.ebreshna.configuration.office.model.OfficeMaster;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

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
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;

    @ManyToOne(targetEntity = AreaMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AreaMaster areaMaster;

    @ManyToOne(targetEntity = MeterReaderMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MeterReaderMaster meterReaderMaster;

    @ManyToOne(targetEntity = DistributionTransformerMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DistributionTransformerMaster distributionTransformerMaster;

    @ManyToOne(targetEntity = MeterBoxMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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
