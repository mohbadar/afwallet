package af.gov.anar.ebreshna.configuration.metering.office_cycle_relation;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.metering.cycle.CycleConfiguration;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "metering_office_cycle_relation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class OfficeCycleRelation extends BaseEntity {

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = CycleConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "cycle_configuration_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CycleConfiguration cycleConfiguration;

    @OneToOne(targetEntity = OfficeMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "office_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;
}
