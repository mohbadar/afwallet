package af.gov.anar.ebreshna.configuration.network.substation;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.enumeration.SubstationCapacity;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.configuration.office.office_type.OfficeType;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

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
    @JoinColumn(name = "office_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;

    @Column
    private String substationCapacity;
    @Column
    private String stationName;
    @Column
    private String stationCode;
    @Column
    private String stationLocation;

}