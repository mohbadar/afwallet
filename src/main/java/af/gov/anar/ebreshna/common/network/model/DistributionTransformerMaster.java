package af.gov.anar.ebreshna.common.network.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.network.enumeration.DTRLocation;
import af.gov.anar.ebreshna.common.network.enumeration.EnergyMeterSide;
import af.gov.anar.ebreshna.common.network.enumeration.TypeOfMounting;
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
import java.awt.geom.Area;
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
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class)
    private OfficeMaster officeMaster;


    @ManyToOne(targetEntity = SubstationMaster.class)
    private SubstationMaster substationMaster;

    @ManyToOne(targetEntity = StationMaster.class)
    private StationMaster stationMaster;

    @ManyToOne(targetEntity = FeederMaster.class)
    private FeederMaster feederMaster;

    @ManyToOne(targetEntity = AreaMaster.class)
    private AreaMaster areaMaster;

    @ManyToOne(targetEntity = VoltageLevel.class)
    private VoltageLevel lowVoltageLevel;

    @Column
    private Date installationDate;

    @Column
    private Date guaranteeDate;

    @Column
    private String remark;

    @ManyToOne(targetEntity = MeterReaderMaster.class)
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
