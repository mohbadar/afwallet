package af.gov.anar.ebreshna.configuration.office.office;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.network.substation.SubstationMaster;
import af.gov.anar.ebreshna.configuration.office.office_type.OfficeType;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "office_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class OfficeMaster extends BaseEntity {

    @ManyToOne(targetEntity = OfficeType.class)
    private OfficeType officeType;

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JsonBackReference
    private OfficeMaster parent;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String officeCode;
    @Column(nullable = false)
    private String shortName;
    private String doorNumber;
    private String street;
    private String city;
    private String phone1;
    private String phone2;
    private String phone3;
    private String fax;
    private String email;
//    private String hodOfJunction;

    @OneToMany(mappedBy = "officeMaster")
    private List<SubstationMaster> substationMasters;


}
