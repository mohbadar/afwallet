package af.gov.anar.ebreshna.customerservice.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.office.model.OfficeMaster;
import af.gov.anar.ebreshna.office.model.PremisesCategory;
import lombok.*;
import org.hibernate.envers.Audited;

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
    private RequestType requestType;

    private String requestNature;

    private String serviceLevel;

    private Date effectiveDate;

    private boolean autoDispatech;

    @ManyToOne(targetEntity = PremisesCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "office_master_id")
    private OfficeMaster officeMaster;


    private boolean escalationRequired;
    private String duration;
    private String firstEscalation;
    private String secondEscalation;



    private boolean paymentRequired;
    private boolean customerNumberRequired;
    private boolean displayRequestNature;

}
