package af.gov.anar.ebreshna.configuration.metering.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.metering.enumeration.AlertType;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "metering_zone_cycle_relation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ZoneCycleRelation extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = Zone.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "zone_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Zone zone;

    @Column
    private Date startDate;

    @Column
    private Date plannedStartDate;

    @Column
    private double graceDays;

    @OneToOne(targetEntity = ActivityMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "activity_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private ActivityMaster activityMaster;

    @OneToOne(targetEntity = CycleConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "cycle_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CycleConfiguration cycleConfiguration;


    @Column
    private Date endDate;

    @Column
    private Date plannedEndDate;

    @Column
    private double preAlertDays;

    @Column
    private AlertType alertType;
}
