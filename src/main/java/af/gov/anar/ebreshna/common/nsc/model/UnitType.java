package af.gov.anar.ebreshna.common.nsc.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nsc_unit_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class UnitType extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
}
