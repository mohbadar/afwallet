package af.gov.anar.ebreshna.configuration.nsc.model;


import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nsc_applaince_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ApplianceMaster extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
