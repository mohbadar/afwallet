package af.gov.anar.ebreshna.configuration.nsc.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.nsc.enumeration.ItemHead;
import af.gov.anar.ebreshna.configuration.nsc.enumeration.TypeOfWork;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "nsc_material_estimate_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class MaterialEstimateMaster extends BaseEntity {

    @Column
    private ItemHead itemHead;

    @ManyToOne(targetEntity = UnitType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_type_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private UnitType unitType;

    @Column
    private int assetManagementNo;

    @Column
    private String itemName;

    @Column
    private double itemCost;

    @Column
    private  String itemWork;

    @Column
    private boolean majorCategory;

    @Column
    private String snapPlant;

    @Column
    private TypeOfWork typeOfWork;
}
