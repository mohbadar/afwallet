package af.gov.anar.ebreshna.configuration.payment.fee_penalty;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.fee.model.FeeType;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import af.gov.anar.ebreshna.configuration.nsc.supply_voltage.SupplyVoltage;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_fee_penalty_configuration", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class FeePenaltyConfiguration extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = RequestType.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "request_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private RequestType requestType;

    @OneToOne(targetEntity = TariffCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "tariff_category_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCategory tariffCategory;


    @Column
    private double minLoad;

    @Column
    private  double maxLoad;

    @OneToOne(targetEntity = SupplyVoltage.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "supply_voltage_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private SupplyVoltage supplyVoltage;

    @Column
    private String phase;


    @OneToOne(targetEntity = FeeType.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "fee_type_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private FeeType feeType;

    @Column
    private Date effectiveDate;

    @Column
    private boolean taxApplicable;



}
