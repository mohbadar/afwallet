package af.gov.anar.ebreshna.csc.complaint.complaint_request_type;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "csc_complaint_request_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ComplaintRequestType extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
}
