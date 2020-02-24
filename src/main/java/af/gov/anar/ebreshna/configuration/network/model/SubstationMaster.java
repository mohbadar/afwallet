package af.gov.anar.ebreshna.configuration.network.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.enumeration.SubstationCapacity;
import af.gov.anar.ebreshna.configuration.office.model.OfficeMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "network_substation_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class SubstationMaster extends BaseEntity {

    @ManyToOne(targetEntity = OfficeMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;

    @Column
    private SubstationCapacity substationCapacity;

    @Column
    private String stationName;
    @Column
    private String stationCode;
    @Column
    private String stationLocation;

}
