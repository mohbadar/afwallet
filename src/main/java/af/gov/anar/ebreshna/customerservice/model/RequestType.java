package af.gov.anar.ebreshna.customerservice.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "crm_request_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class RequestType extends BaseEntity {

    private String type;
}
