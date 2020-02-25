package af.gov.anar.ebreshna.configuration.billing.model;

import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffModifyCategory;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.csc.model.ApprovalLimit;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "billing_tariff_category_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class TariffCategoryMaster extends BaseEntity {

    @ManyToOne(targetEntity = TariffCategoryTypeMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "tariff_category_master_id")
    private TariffCategoryTypeMaster tariffCategoryTypeMaster;

    @Column
    private TariffModifyCategory tariffModifyCategory;

    @Column
    private String tariffName;

    @Column
    private String tariffCode;


}
