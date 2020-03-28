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
@Table(name = "patient_travel_history")
public class PatientTravelHistory extends BaseEntity {

    @ManyToOne(targetEntity = Country.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_country_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Country fromCountry;

    @ManyToOne (targetEntity = Country.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province fromProvince;

    @ManyToOne(targetEntity = Country.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_country_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Country toCountry;

    @ManyToOne (targetEntity = Country.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province toProvince;

    @Column
    private int fromYear;

    @Column
    private int fromMonth;

    @Column
    private int fromDay;

    @Column
    private int toYear;

    @Column
    private int toMonth;

    @Column
    private int toDay;
}
