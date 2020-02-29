package af.gov.anar.ebreshna.metering;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "metering_meter_applicant_relation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class MeterApplicantRelation extends BaseEntity {

    @OneToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "applicant_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Applicant applicant;


    @OneToOne(targetEntity = Meter.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "meter_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Meter meter;


    /**
     *     • MTROPNRDG
     *     • MTROPNRDGDT
     *
     */

    @Column
    private String meterLocation;

    @Column
    private String meterHeight;

    @Column
    private String multTpf;

    @Column
    private String meterSiteAddress1;

    @Column
    private String meterSiteAddress2;

    @Column
    private String meterSiteAddress3;

    @Column
    private String meterSiteAddress4;

    @Column
    private String remark;

    @Column
    private String meterCurrentStat;


    @Column
    private String meterReadingAtOpening;

    @Column
    private Date meterReadingAtOpeningDate;


}
