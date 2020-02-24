package af.gov.anar.ebreshna.configuration.office.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "crm_premises_sub_category", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class PremisesSubCategory extends BaseEntity {
}
