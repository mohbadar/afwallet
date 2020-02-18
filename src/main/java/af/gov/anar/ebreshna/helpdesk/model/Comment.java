package af.gov.anar.ebreshna.helpdesk.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Comment  extends BaseEntity {

    @ManyToOne(targetEntity = Complaint.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    @JsonIgnore
    private Complaint complaint;

    private String content;

    private String username;


}
