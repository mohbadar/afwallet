package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.common.model.BaseEntity;
import af.gov.anar.ebreshna.common.province.Province;
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


    private Province province;

}
