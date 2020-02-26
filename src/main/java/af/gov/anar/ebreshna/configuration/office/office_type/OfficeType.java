package af.gov.anar.ebreshna.configuration.office.office_type;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

//    @OneToOne(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
//    @JoinColumn(name="parent")
//    private OfficeType officeType;

    @ManyToOne(targetEntity = OfficeType.class , fetch = FetchType.EAGER)
    @JoinColumn(name = "parent", referencedColumnName = "id")
    @JsonBackReference
    private OfficeType officeType;
}
