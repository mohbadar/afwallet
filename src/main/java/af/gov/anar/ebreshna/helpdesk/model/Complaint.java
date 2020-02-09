package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.common.model.BaseEntity;
import af.gov.anar.ebreshna.common.province.Province;
import af.gov.anar.ebreshna.helpdesk.enumeration.ModuleType;
import af.gov.anar.lib.workflow.model.Workflow;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

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

    private Workflow workflow;

    private Province province;

    private String junction;

    private ModuleType moduleType;

    private String mobileNumber;

    private String attachmentPath;

    private String content;



}
