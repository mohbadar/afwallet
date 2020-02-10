package af.gov.anar.ebreshna.customerservice.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.province.Province;
import af.gov.anar.ebreshna.office.model.PremisesCategory;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "crm_approval_limit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ApprovalLimit extends BaseEntity {

    @ManyToOne(targetEntity = ApprovalLimit.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "approval_limit_id")
    private ApprovalLimit approvalLimit;

    @ManyToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    private Province province;

    private BigDecimal amount;
}
