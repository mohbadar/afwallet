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
@Table(name = "patient_contact")
public class PatientContact extends BaseEntity {

    @Column
    private String familyName;

    @Column
    private String firstName;

    @Column
    private String relationship;

    @Column
    private String otherName;

    @Column
    private String phoneNo;

    @Column
    private String mobileNo;

    @Column
    private String faxNo;

    @Column
    private String email;


}
