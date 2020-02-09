package af.gov.anar.ebreshna.common.station.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "station_type", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class StationType extends BaseEntity {

    private String name;
}
