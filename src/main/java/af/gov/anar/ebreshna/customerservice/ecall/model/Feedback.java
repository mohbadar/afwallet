package af.gov.anar.ebreshna.customerservice.ecall.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.csc.model.Request;
import af.gov.anar.ebreshna.configuration.csc.model.RequestType;
import af.gov.anar.ebreshna.configuration.office.model.OfficeMaster;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customerservice_ivr_call_feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Feedback extends BaseEntity {

    @ManyToOne(targetEntity = IvrCall.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ivr_call_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private IvrCall ivrCall;


    @ManyToOne(targetEntity = RequestType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "request_type_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private RequestType requestType;


    @ManyToOne(targetEntity = Request.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Request request;

    @Column
    private Date registeredOn;

    @Column
    private String registeredBy;

    @Column
    private String requestDetails;

    @Column
    private String complainantName;

    @Column
    private  String email;

    @Column
    private String phoneNo;

    @ManyToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @ManyToOne(targetEntity = OfficeMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "office_master_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;

}
