package af.gov.anar.ebreshna.configuration.network.feeder;


import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.enumeration.*;
import af.gov.anar.ebreshna.configuration.network.station_type.StationType;
import af.gov.anar.ebreshna.configuration.network.substation.SubstationMaster;
import af.gov.anar.ebreshna.configuration.network.voltage_level.VoltageLevel;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "network_feeder_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class FeederMaster extends BaseEntity {

    @ManyToOne(targetEntity = StationType.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private StationType stationType;

    @ManyToOne(targetEntity = SubstationMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private SubstationMaster stationMaster;

    @Column
    private String feederName;

    @Column(name="feeder_power_supply")
    @Enumerated(EnumType.STRING)
    private FeederPowerSupply feederPowerSupply;

    @Column(name="feeder_type")
    @Enumerated(EnumType.STRING)
    private FeederType feederType;

    @Column
    private String feederCode;

    @Column
    private String feederAt;

    @Column(name="organization")
    @Enumerated(EnumType.STRING)
    private FeederOrganization organization;

    @Column
    private String feederLocation;

    @Column(name="feeder_category")
    @Enumerated(EnumType.STRING)
    private FeederCategory feederCategory;

    @Column(name="feeder_load_shed_category")
    @Enumerated(EnumType.STRING)
    private FeederLoadShedCatogory feederLoadShedCategory;

    @Column
    private String maxLoad;

    @ManyToOne(targetEntity = VoltageLevel.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private VoltageLevel voltageLevel;

    @Column
    private String ain;

}
