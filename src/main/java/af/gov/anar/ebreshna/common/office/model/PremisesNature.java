package af.gov.anar.ebreshna.common.office.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "crm_premises_nature", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class PremisesNature extends BaseEntity {

    @ManyToOne(targetEntity = PremisesCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "premises_category_id")
    private PremisesCategory premisesCategory;

    @ManyToOne(targetEntity = PremisesCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "premises_sub_category_id")
    private PremisesSubCategory premisesSubCategory;

    @Column(nullable = false)
    private String premisesNature;
}
