package af.gov.anar.ebreshna.common.office.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "office_type", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class OfficeType extends BaseEntity {

    @Column(unique = true)
    private String name;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent")
    private OfficeType officeType;
}
