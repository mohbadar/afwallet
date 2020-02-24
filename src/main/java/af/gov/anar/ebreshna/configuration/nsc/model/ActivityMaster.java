package af.gov.anar.ebreshna.configuration.nsc.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.nsc.enumeration.ModuleType;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nsc_activity_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ActivityMaster extends BaseEntity {

    @Column(nullable = false)
    private  String activity;

    @Column
    private String serviceLevel;

    @Column
    private ModuleType moduleType;

    @Column
    private boolean skippable;

    @Column
    private boolean displayStatus;

    @Column
    private boolean mandatory;
}
