package af.gov.anar.ebreshna.nsc.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "nsc_applicant_communication_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class CommunicationAddress extends BaseEntity {

    @ManyToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "applicant_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Applicant applicant;

}
