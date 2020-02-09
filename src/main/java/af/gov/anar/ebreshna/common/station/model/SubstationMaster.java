package af.gov.anar.ebreshna.common.station.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.station.enumeration.SubstationCapacity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notification", schema = Schema.HELP_DESK_DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class SubstationMaster extends BaseEntity {

    private String name;
    private SubstationCapacity substationCapacity;
    private String substationCode;
    private String substationLocation;
//    private Junct
}
