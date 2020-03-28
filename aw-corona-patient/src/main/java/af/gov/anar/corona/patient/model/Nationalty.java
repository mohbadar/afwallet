package af.gov.anar.corona.patient.model;

import af.gov.anar.corona.infrastructure.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
//@Table(schema = Schema.CORE_SCHEMA, name = "nationality")
public class Nationalty extends BaseEntity {

    @Column
    private String desc;

    @Column
    private String descF;

    @Column
    private String descS;
}
