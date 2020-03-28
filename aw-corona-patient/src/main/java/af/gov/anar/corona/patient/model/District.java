package af.gov.anar.corona.patient.model;

import af.gov.anar.corona.infrastructure.base.BaseEntity;
import af.gov.anar.corona.infrastructure.util.Schema;
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
@Table(name = "district")
public class District extends BaseEntity {

    @Column
    private String desc;
    @Column
    private String desF;
    @Column
    private String desS;

    @Column(unique = true)
    private String districtCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(referencedColumnName = "id", name = "province_id")
    private Province province;
}
