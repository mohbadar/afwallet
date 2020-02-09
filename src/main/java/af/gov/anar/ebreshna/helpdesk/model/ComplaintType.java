package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.helpdesk.enumeration.ComplaintPriority;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
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
public class ComplaintType extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private ComplaintPriority complaintPriority;
}
