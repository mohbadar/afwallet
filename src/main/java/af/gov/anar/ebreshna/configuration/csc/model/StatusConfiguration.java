package af.gov.anar.ebreshna.configuration.csc.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.helpdesk.enumeration.ComplaintStatus;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "crm_status_configuration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class StatusConfiguration extends BaseEntity {

    private String status;
    private String statusValue;
    private String  statusDescription;
    private ComplaintStatus complaintStatus;
    private boolean skipable;
    private boolean displayStatus;
    private boolean mandatory;


}
