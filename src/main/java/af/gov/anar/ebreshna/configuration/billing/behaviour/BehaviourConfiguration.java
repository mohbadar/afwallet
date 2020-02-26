package af.gov.anar.ebreshna.configuration.billing.behaviour;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "billing_behaviour_configuration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class BehaviourConfiguration extends BaseEntity {

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String remark;

    @Column
    private boolean paymentHead;

    @ManyToOne(targetEntity = RequestType.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "request_type_id")
    private RequestType requestType;

    @Column
    private boolean display;
}
