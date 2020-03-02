package af.gov.anar.ebreshna.configuration.csc.request;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.configuration.office.premises_category.PremisesCategory;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "crm_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Request extends BaseEntity {

    @ManyToOne(targetEntity = PremisesCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "request_type_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private RequestType requestType;

    private String requestNature;

    private String serviceLevel;

    private Date effectiveDate;

    private boolean autoDispatech;

    @ManyToOne(targetEntity = PremisesCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "office_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;


    private boolean escalationRequired;
    private String duration;
    private String firstEscalation;
    private String secondEscalation;

    @ManyToOne(targetEntity = TariffCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "pending_belong_to")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCategory pendingBelongTo;

    @ManyToOne(targetEntity = TariffCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "first_escalation_to")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCategory firstEscalationTo;

    @ManyToOne(targetEntity = TariffCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "second_escalation_to")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCategory secondEscalationTo;



    private boolean paymentRequired;
    private Date payableAt;


    private boolean customerNumberRequired;
    private boolean displayRequestNature;

}
