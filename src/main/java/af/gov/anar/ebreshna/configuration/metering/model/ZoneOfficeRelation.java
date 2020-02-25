package af.gov.anar.ebreshna.configuration.metering.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "metering_zone_office_relation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ZoneOfficeRelation extends BaseEntity {
}
