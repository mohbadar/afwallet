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
@Table(name = "patient_address")
public class PatientAddress extends BaseEntity {

    @ManyToOne (targetEntity = Country.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private District district;

    @ManyToOne (targetEntity = Country.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private City city;

    @Column
    private String village;

    @Column(name = "street")
    private String street;

    @Column
    private String description;


    private String address1;

    private String address2;

    private String address3;

}
