package af.gov.anar.corona.patient.model;

import af.gov.anar.corona.infrastructure.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Table( name = "country")
public class Country extends BaseEntity {

    @Column
    private String desc;
    @Column
    private String desF;
    @Column
    private String desS;

    @Column(unique = true)
    private String countryCode;
}
