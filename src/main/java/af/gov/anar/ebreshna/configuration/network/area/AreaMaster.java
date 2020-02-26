package af.gov.anar.ebreshna.configuration.network.area;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.enumeration.AreaType;
import af.gov.anar.ebreshna.configuration.office.model.DesignationMaster;
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
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;


    @ManyToOne(targetEntity = DesignationMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DesignationMaster reportingTo;

    @Column(unique = true, nullable = false)
    private String areaName;
    @Column(unique = true, nullable = false)
    private String areaCode;
    @Column
    private AreaType areaType;
}
