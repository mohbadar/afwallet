package af.gov.anar.corona.patient.model;

import af.gov.anar.corona.infrastructure.base.BaseEntity;
import af.gov.anar.corona.patient.enumeration.Certainty;
import af.gov.anar.corona.patient.enumeration.ConditionClinicalStatus;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
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

    @OneToMany(targetEntity = PatientAddress.class, fetch = FetchType.EAGER)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private List<PatientAddress> patientAddresses;

    @OneToMany(targetEntity = PatientContact.class, fetch = FetchType.EAGER)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private List<PatientContact> patientContacts;
}
