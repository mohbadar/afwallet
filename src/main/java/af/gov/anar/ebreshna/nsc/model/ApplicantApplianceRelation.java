package af.gov.anar.ebreshna.nsc.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import af.gov.anar.ebreshna.configuration.nsc.appliance.ApplianceMaster;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "nsc_applicant_appliance_relation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ApplicantApplianceRelation extends BaseEntity {

    @ManyToOne(targetEntity = ApplianceMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "appliance_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private ApplianceMaster applianceMaster;

    @ManyToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "applicant_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Applicant applicant;

    @Column
    private int count;
}
