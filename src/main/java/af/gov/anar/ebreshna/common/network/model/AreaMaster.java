package af.gov.anar.ebreshna.common.network.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.network.enumeration.AreaType;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.model.OfficeMaster;
import af.gov.anar.ebreshna.common.office.model.OfficeType;
import af.gov.anar.ebreshna.common.province.Province;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "network_area_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class AreaMaster extends BaseEntity {

    @ManyToOne(targetEntity = Province.class)
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class)
    private OfficeMaster officeMaster;


    @ManyToOne(targetEntity = DesignationMaster.class)
    private DesignationMaster reportingTo;

    @Column(unique = true, nullable = false)
    private String areaName;
    @Column(unique = true, nullable = false)
    private String areaCode;
    @Column
    private AreaType areaType;
}
