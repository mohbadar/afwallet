package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.base.province.Province;
import af.gov.anar.ebreshna.helpdesk.enumeration.ModuleType;
import af.gov.anar.lib.workflow.model.Workflow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "complaint")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Complaint  extends BaseEntity {

    private String complainantName;

    private String email;

    @OneToOne(targetEntity = Workflow.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "workflow_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Workflow workflow;

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @OneToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "complaint_type_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private ComplaintType complaintType;

    private String junction;

    private ModuleType moduleType;

    private String mobileNumber;

    private String attachmentPath;

    private String remarks;

    @OneToMany(mappedBy = "complaint", fetch = FetchType.LAZY)
    @JsonIgnore
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Collection<Comment> comments;


    @OneToMany(mappedBy = "complaint", fetch = FetchType.LAZY)
    @JsonIgnore
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Collection<ComplaintHistory> complaintHistories;

    @Column
    private String assignee;

    @Column
    private boolean suggestion;

}
