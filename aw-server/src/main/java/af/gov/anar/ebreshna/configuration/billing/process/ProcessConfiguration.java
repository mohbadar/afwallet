package af.gov.anar.ebreshna.configuration.billing.process;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "billing_process_configuration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ProcessConfiguration extends BaseEntity {

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String remark;

    @Column
    private String priority;
}