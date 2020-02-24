package af.gov.anar.ebreshna.configuration.network.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.enumeration.DTRLocation;
import af.gov.anar.ebreshna.configuration.network.enumeration.EnergyMeterSide;
import af.gov.anar.ebreshna.configuration.network.enumeration.TypeOfMounting;
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
import java.util.Date;

@Entity
@Table(name = "network_distribution_transformer_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class DistributionTransformerMaster extends BaseEntity {

    @ManyToOne(targetEntity = Province.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;


    @ManyToOne(targetEntity = SubstationMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private SubstationMaster substationMaster;

    @ManyToOne(targetEntity = StationMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private StationMaster stationMaster;

    @ManyToOne(targetEntity = FeederMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private FeederMaster feederMaster;

    @ManyToOne(targetEntity = AreaMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AreaMaster areaMaster;

    @ManyToOne(targetEntity = VoltageLevel.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private VoltageLevel lowVoltageLevel;

    @Column
    private Date installationDate;

    @Column
    private Date guaranteeDate;

    @Column
    private String remark;

    @ManyToOne(targetEntity = MeterReaderMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MeterReaderMaster meterReaderMaster;

    @Column
    private String dtrName;

    @Column
    private String dtrCode;

    @Column
    private String dtrMapCode;

    @Column
    private String dtrLocation;

    @Column
    private String ain;

    @Column
    private String maxCapacity;

    @Column
    private String primaryVoltage;

    @Column
    private String secondaryVoltage;

    @Column(name = "transformer_location")
    private DTRLocation DTRLocation;

    @Column
    private TypeOfMounting typeOfMounting;

    @Column
    private EnergyMeterSide energyMeterSide;

    @ManyToOne(targetEntity = DtrCategory.class)
    private DtrCategory dtrCategory;



}
