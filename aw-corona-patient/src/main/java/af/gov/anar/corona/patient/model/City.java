package af.gov.anar.corona.patient.model;

import af.gov.anar.corona.infrastructure.base.BaseEntity;
import af.gov.anar.corona.infrastructure.base.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Table(name = "city")
public class City extends BaseEntity {

    @Column
    private String cityName;

    @Column
    private String cityNameF;

    @Column
    private String citryNamaS;

    @Column
    private String cityCode;

    @Column
    private String cityNameExtra;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(referencedColumnName = "id", name = "province_id")
    private Province province;

}
