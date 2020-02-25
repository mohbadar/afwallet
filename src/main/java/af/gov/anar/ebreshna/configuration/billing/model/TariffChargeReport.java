package af.gov.anar.ebreshna.configuration.billing.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.metering.model.CycleConfiguration;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "billing_tariff_charge_report")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class TariffChargeReport extends BaseEntity {

    @ManyToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    private Province province;


    @ManyToOne(targetEntity = TariffCategoryMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "tariff_category_master_id")
    private TariffCategoryMaster tariffCategoryMaster;

    @Column
    private Date effectDate;

    @ManyToOne(targetEntity = TariffCharge.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "tariff_charges")
    private CycleConfiguration cycleConfiguration;

}
