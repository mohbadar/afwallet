package af.gov.anar.ebreshna.office.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

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

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent")
    private OfficeMaster officeMaster;

    private String name;
    private String officeCode;
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


}
