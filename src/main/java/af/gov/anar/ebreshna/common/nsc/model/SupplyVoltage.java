package af.gov.anar.ebreshna.common.nsc.model;


import af.gov.anar.ebreshna.common.base.BaseEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nsc_supply_voltage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class SupplyVoltage extends BaseEntity {

    @Column
    private String name;
}