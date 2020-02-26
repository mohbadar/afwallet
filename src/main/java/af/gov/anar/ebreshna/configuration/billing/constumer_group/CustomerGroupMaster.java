package af.gov.anar.ebreshna.configuration.billing.constumer_group;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "billing_customer_group_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class CustomerGroupMaster extends BaseEntity {

    @Column
    private String groupCode;

    @Column
    private String groupName;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String address3;

    @Column
    private String address4;

}
