package af.gov.anar.ebreshna.configuration.metering.activity_relation;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.metering.meter_activity.MeteringActivityMaster;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "metering_activity_relation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ActivityRelation extends BaseEntity {

    @Column
    private String name;

    @OneToOne(targetEntity = MeteringActivityMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "activity_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MeteringActivityMaster activityMaster;
}