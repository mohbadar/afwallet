package af.gov.anar.ebreshna.configuration.network.substation;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.enumeration.SubstationCapacity;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
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
//@TypeDef(
//        name = "pgsql_enum",
//        typeClass = PostgreSQLEnumType.class
//)
public class SubstationMaster extends BaseEntity {

    @ManyToOne(targetEntity = OfficeMaster.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;

//    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "substation_status_info")
//    @Type( type = "pgsql_enum" )
    private String substationCapacity;

    @Column
    private String stationName;
    @Column
    private String stationCode;
    @Column
    private String stationLocation;

}
