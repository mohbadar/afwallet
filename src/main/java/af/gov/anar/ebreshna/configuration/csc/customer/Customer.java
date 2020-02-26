package af.gov.anar.ebreshna.configuration.csc.customer;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.csc.request.Request;
import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import af.gov.anar.ebreshna.configuration.office.model.PremisesCategory;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "crm_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Customer extends BaseEntity {

    @ManyToOne(targetEntity = PremisesCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "request_type_id")
    private RequestType requestType;

    @ManyToOne(targetEntity = PremisesCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "premises_category_id")
    private Request premisesCategory;
}
