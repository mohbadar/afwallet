package af.gov.anar.ebreshna.nsc.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import af.gov.anar.ebreshna.configuration.network.area.AreaMaster;
import af.gov.anar.ebreshna.configuration.network.feeder.FeederMaster;
import af.gov.anar.ebreshna.configuration.office.office.OfficeMaster;
import af.gov.anar.ebreshna.nsc.enumeration.ContactMethod;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nsc_applicant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class Applicant extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fatherOrGuardianName;

    @Column
    private String addressLine1;

    @Column
    private  String addressLine2;

    @ManyToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "province_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Province province;


    @ManyToOne(targetEntity = OfficeMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "office_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private OfficeMaster officeMaster;

    @ManyToOne(targetEntity = FeederMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "feeder_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private FeederMaster feederMaster;

    @ManyToOne(targetEntity = AreaMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "area_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AreaMaster areaMaster;

    @Column
    private String fileUpload;

    @Column
    private String applicantPhotoGraph;

    @Column
    private String applicantSignature;

    @Column
    private Date registeredOn;


    @Column
    private String applicationNumber;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String faxNumber;

    @Column
    private ContactMethod contactMethod;


    @OneToOne(targetEntity = CommunicationAddress.class, fetch = FetchType.EAGER)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CommunicationAddress communicationAddress;


    @ManyToOne(targetEntity = TariffCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "tariff_category_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private TariffCategory tariffCategory;


}
