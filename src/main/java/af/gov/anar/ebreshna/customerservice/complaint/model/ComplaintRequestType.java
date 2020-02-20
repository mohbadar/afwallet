package af.gov.anar.ebreshna.customerservice.complaint.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customerservice_complaint_type")
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
