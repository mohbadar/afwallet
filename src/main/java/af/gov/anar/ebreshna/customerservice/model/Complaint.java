package af.gov.anar.ebreshna.customerservice.model;


import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.base.province.Province;
import af.gov.anar.ebreshna.common.base.workflowdata.WorkflowTransitionData;
import af.gov.anar.ebreshna.common.csc.model.Request;
import af.gov.anar.ebreshna.common.csc.model.RequestType;
import af.gov.anar.ebreshna.common.network.model.DtrCategory;
import af.gov.anar.lib.workflow.model.Workflow;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customerservice_complaint")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Complaint extends BaseEntity {

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


    @Column
    private String tazkiraDocument;
    @Column
    private String identityProofDocument;

    @Column
    private String customerAccountBookDocument;


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
