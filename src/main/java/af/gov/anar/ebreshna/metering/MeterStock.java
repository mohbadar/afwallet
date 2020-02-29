package af.gov.anar.ebreshna.metering;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "metering_meter_stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class MeterStock extends BaseEntity {


    @Column
    private String meterId;

    @Column
    private String meter_number;

    @Column
    private String meterType;

    @Column
    private String meterPhase;

    @Column
    private  String meterCapacity;

    @Column
    private String meterConstant;

    @Column
    private String meterDegit;

    @Column
    private String meterElecType;

    @Column
    private String meterSealDt;

    @Column
    private String ctType;

    @OneToOne(targetEntity = OfficeMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "office_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;
    @Column
    private String meterDetail;

    @Column
    private String meterAmps;

    @Column
    private String meterVolts;

    @Column
    private String IndentNo;

    @Column
    private boolean checkCondition;

    @Column
    private String recordStatus;

    @Column
    private String remarks;

    @Column
    private String meterSlno;

    @Column
    private String meterSealNo1;

    @Column
    private String meterSealNo2;

    @Column
    private String meterSealNo3;

    @Column
    private String meterSealNo4;

    @Column
    private boolean meterAvailable;

    @Column
    private String oldMeterNo;

    @Column
    private String meterPhaseNea;

}
