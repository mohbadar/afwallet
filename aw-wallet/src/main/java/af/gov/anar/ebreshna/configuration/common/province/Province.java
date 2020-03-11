package af.gov.anar.ebreshna.configuration.common.province;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Table(schema = Schema.CORE_SCHEMA, name = "province")
public class Province extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "province_code", nullable = false)
    private String provinceCode;



}
