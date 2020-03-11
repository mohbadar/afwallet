package af.gov.anar.ebreshna.csc.ivr_call;


import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.common.workflowdata.WorkflowTransitionData;
import af.gov.anar.ebreshna.configuration.csc.request.Request;
import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import af.gov.anar.lib.workflow.model.Workflow;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customerservice_ivr_call")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class IvrCall extends BaseEntity {

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




    @ManyToOne(targetEntity = Workflow.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "workflow_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Workflow workflow;

    @Column
    private String workflowCurrentStep;


    @ManyToOne(targetEntity = WorkflowTransitionData.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "workflow_transition_data_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private WorkflowTransitionData workflowTransitionData;


}
