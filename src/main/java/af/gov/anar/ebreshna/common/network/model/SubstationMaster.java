package af.gov.anar.ebreshna.common.network.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.network.enumeration.SubstationCapacity;
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
