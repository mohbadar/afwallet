package af.gov.anar.ebreshna.configuration.csc.request_type;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "csc_request_type")
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
