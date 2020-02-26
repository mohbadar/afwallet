package af.gov.anar.ebreshna.configuration.office.field_staff;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.office.designation.DesignationMaster;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import af.gov.anar.ebreshna.configuration.office.enumeration.Gender;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

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
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;

    @OneToOne(targetEntity = DesignationMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "designation_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DesignationMaster designationMaster;

    @Column(unique = true)
    private String employeeCode;

    private String name;

    private  String surname;

    private Gender gender;

}
