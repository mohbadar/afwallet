package af.gov.anar.ebreshna.common.dlist.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.common.base.province.Province;
import af.gov.anar.ebreshna.common.dlist.enumeration.DListType;
import af.gov.anar.ebreshna.common.nsc.model.ApplianceMaster;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "dlist_dlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class DList extends BaseEntity {

    @ManyToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;

    @Column
    private DListType dListType;

}
