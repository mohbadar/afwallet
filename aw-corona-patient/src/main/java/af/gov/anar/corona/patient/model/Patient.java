package af.gov.anar.corona.patient.model;

import af.gov.anar.corona.infrastructure.base.BaseEntity;
import af.gov.anar.corona.patient.enumeration.Certainty;
import af.gov.anar.corona.patient.enumeration.ConditionClinicalStatus;
import af.gov.anar.corona.patient.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Table(name = "patient")
public class Patient extends BaseEntity {

    private String firstName;

    private String lastName;

    private String tazkiraSerialNumber;

    private  int tazkiraPage;

    private String tazkiraJuld;

    private int tazkiraShumera;

    private String eTazkiraNumber;

    private String phoneNo1;

    private String phoneNo2;

    private String email;

    private long dob;

    private String job;


    @Enumerated(value = EnumType.STRING)
    private Certainty certainty;

    @Enumerated(value = EnumType.STRING)
    private ConditionClinicalStatus conditionClinicalStatus;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(targetEntity = PatientAddress.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private List<PatientAddress> patientAddresses;

    @OneToMany(targetEntity = PatientContact.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private List<PatientContact> patientContacts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "patient_nationality", joinColumns = @JoinColumn(name = "nationalty_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Nationality> mapLayers;
}
