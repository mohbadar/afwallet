package af.gov.anar.ebreshna.office.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.province.Province;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import af.gov.anar.ebreshna.office.enumeration.Gender;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "crm_field_staff", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class FieldStaff extends BaseEntity {

    @OneToOne(targetEntity = OfficeMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "office_master_id")
    private OfficeMaster officeMaster;

    @OneToOne(targetEntity = DesignationMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "designation_master_id")
    private DesignationMaster designationMaster;

    @Column(unique = true)
    private String employeeCode;

    private String name;

    private  String surname;

    private Gender gender;

}
