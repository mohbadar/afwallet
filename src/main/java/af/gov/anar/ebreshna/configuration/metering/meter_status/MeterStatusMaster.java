package af.gov.anar.ebreshna.configuration.metering.meter_status;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "metering_meter_status_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class MeterStatusMaster extends BaseEntity {

    @Column
    private String meterStatusCode;

    @Column
    private String meterStatus;

    @Column
    private String meterName;

    @OneToOne(targetEntity = BehaviourConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "behaviour_configuration_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private BehaviourConfiguration behaviourConfiguration;

    @Column
    private String billingBasis;

    @Column
    private String govtLetter;

    @Column
    private String remark;



}
