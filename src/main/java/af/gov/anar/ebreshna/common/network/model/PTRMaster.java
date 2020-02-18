package af.gov.anar.ebreshna.common.network.model;


import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.office.model.OfficeMaster;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "network_ptr_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class PTRMaster extends BaseEntity {

    @ManyToOne(targetEntity = SubstationMaster.class)
    private SubstationMaster substationMaster;

    @ManyToOne(targetEntity = FeederMaster.class)
    private FeederMaster feederMaster;


    @Column
    private String ptrName;

    @Column
    private String ptrCode;


    @Column
    private String primaryVoltage;

    @Column
    private String secondaryVoltage;

    @Column
    private String ptrCapacity;

    @Column
    private String ain;

}
