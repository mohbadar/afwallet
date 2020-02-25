package af.gov.anar.ebreshna.configuration.billing.model;

import af.gov.anar.ebreshna.configuration.billing.enumeration.TariffModifyCategory;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import af.gov.anar.ebreshna.configuration.csc.model.ApprovalLimit;
import af.gov.anar.ebreshna.configuration.metering.model.MeteringType;
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
    @JoinColumn(nullable = true, name = "tariff_category_type_id")
    private TariffCategoryTypeMaster tariffCategoryTypeMaster;

    @Column
    private TariffModifyCategory tariffModifyCategory;

    @Column
    private String tariffName;

    @Column
    private String tariffCode;

    @Column
    private String orderInReport;

    @Column
    private boolean meterRequired;

    @ManyToOne(targetEntity = MeteringType.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "metering_type_id")
    private MeteringType meteringType;

    @ManyToOne(targetEntity = TariffCategoryMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "parent")
    private TariffCategoryMaster parent;

    @Column
    private String authorizedReferenceNumber;

    @Column
    private String remark;
}
