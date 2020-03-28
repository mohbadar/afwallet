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
@Table(name = "patient_past_disease")
public class PatientPastDisease extends BaseEntity {

    @Column
    private String desc;
}
