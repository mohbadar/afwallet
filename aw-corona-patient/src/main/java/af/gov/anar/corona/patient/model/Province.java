package af.gov.anar.corona.patient.model;

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
//@Table(schema = Schema.ADMINISTRATION, name = "province")
public class Province extends BaseEntity {

    @Column(name = "province_name", nullable = false)
    private String desc;

    @Column(name = "province_name_f", nullable = false)
    private String descF;

    @Column(name = "province_name_s", nullable = false)
    private String descS;

    @Column(name = "province_code", nullable = false)
    private String provinceCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(referencedColumnName = "id", name = "country_id")
    private Country country;



}
