package af.gov.anar.ebreshna.nsc.model;

import af.gov.anar.ebreshna.configuration.common.license_type.LicenseType;
import af.gov.anar.ebreshna.configuration.common.power_source.PowerSource;
import af.gov.anar.ebreshna.configuration.common.power_usage_type.PowerUsageType;
import af.gov.anar.ebreshna.nsc.model.Applicant;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "nsc_lpu_applicant_info_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class LpuApplicantInfoDetail {

    @ManyToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "applicant_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Applicant applicant;


    @Column
    private String buildingSpecification;


    @ManyToOne(targetEntity = PowerSource.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "power_source_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private PowerSource powerSource;


    @ManyToOne(targetEntity = LicenseType.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "license_type_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private LicenseType licenseType;


    @ManyToOne(targetEntity = PowerUsageType.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "power_usage_type_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private PowerUsageType powerUsageType;

    @Column
    private String licenseNumber;

    @Column
    private String nidNumber;

    @Column
    private String nidDocument;

    @Column
    private String propertyOwnershipDocument;

    @Column
    private String licenseDocument;


}
