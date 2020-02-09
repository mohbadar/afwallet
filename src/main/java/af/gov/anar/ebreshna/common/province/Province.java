package af.gov.anar.ebreshna.common.province;

import af.gov.anar.ebreshna.common.model.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

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
