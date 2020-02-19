package af.gov.anar.ebreshna.common.nsc.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "nsc_unit_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class UnitMaster extends BaseEntity {

    @ManyToOne(targetEntity = ApplianceMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "appliance_id", nullable = false)
    @JsonIgnore
    private ApplianceMaster applianceMaster;
}
