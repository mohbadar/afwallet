package af.gov.anar.ebreshna.nsc.online.model;

import af.gov.anar.ebreshna.configuration.billing.voltage_unit.VoltageUnit;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.metering.cycle.CycleConfiguration;
import af.gov.anar.ebreshna.configuration.metering.meter_make_detail.MeterMakeDetail;
import af.gov.anar.ebreshna.configuration.network.distributed_transformer.DistributionTransformerMaster;
import af.gov.anar.ebreshna.configuration.network.meter_box.MeterBoxMaster;
import af.gov.anar.ebreshna.configuration.network.meter_reader.MeterReaderMaster;
import af.gov.anar.ebreshna.configuration.nsc.document.DocumentMaster;
import af.gov.anar.ebreshna.configuration.nsc.metric.MetricMaster;
import af.gov.anar.ebreshna.nsc.enumeration.ConnectionType;
import af.gov.anar.ebreshna.nsc.enumeration.ElectricType;
import af.gov.anar.ebreshna.nsc.enumeration.PropertyType;
import af.gov.anar.ebreshna.nsc.online.model.Applicant;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nsc_applicant_info_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ApplicantInfoDetail extends BaseEntity {


    @Column
    private boolean isHouseOwnerAndApplicantDifferent;

    @Column
    private String houseOwner;

    @Column
    private String houseOwnerFatherOrHusbandName;

    @Column
    private String houseOwnerGrantFatherName;


    @Column
    private boolean applicantEmployee;

    @Column
    private String employeeCode;

    @Column
    private String employeeName;

    @Column
    private String employeeOffice;


    @Column
    private boolean billAddressDifferentFromPremisesAddress;

    @Column
    private boolean anyotherMeterIsAlreadyConnectedInThePremesis;

    @Column
    private String connectedAccountNumber;
    @Column
    private String customerName;

    @Column
    private Date connectedDate;

    @Column
    private String fatherOrHusband;

    @Column
    private ConnectionType connectionType;


    @Column
    private int phase;

    @Column
    private int customerMaxCurrentRequirement;

    @Column
    private int customerLoadRequirement;

    @ManyToOne(targetEntity = MetricMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "customer_max_current_requirement_metric_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MetricMaster customerMaxCurrentRequirementMetric;



    @ManyToOne(targetEntity = MetricMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "customer_load_requirement_metric_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MetricMaster customerLoadRequirementMetric;


    @Column
    private String registeredBy;

    @Column
    private String registeredOn;


    @Column
    private PropertyType propertyType;

    @Column
    private double latitude;

    @Column
    private  double longtitude;

    @Column
    private double geoCode;

    @ManyToOne(targetEntity = MeterReaderMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "meter_reader_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MeterReaderMaster meterReaderMaster;

//    @Column
//    private String meterReader;

    @Column
    private String customerId;


    @ManyToOne(targetEntity = CycleConfiguration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "cycle_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CycleConfiguration cycle;

    @Column
    private Date receiptDate;


    @ManyToOne(targetEntity = DistributionTransformerMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "transformer_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DistributionTransformerMaster distributionTransformerMaster;


    @Column
    private String accountNumber;


    @Column
    private int cycleYear;


    @ManyToOne(targetEntity = MeterBoxMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "meter_box_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MeterBoxMaster meterBoxMaster;

    @Column
    private String neighborAccountNumber;

    @Column
    private String receiptNumber;


    @Column
    private String meterNumber;

    @ManyToOne(targetEntity = MeterMakeDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "meter_make_detail_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private MeterMakeDetail meterMakeDetail;

    @ManyToOne(targetEntity = VoltageUnit.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "voltage_unit_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private VoltageUnit voltageUnit;

    @Column
    private ElectricType electricType;

    @Column
    private String meterSerialNo1;

    @Column
    private String meterSerialNo3;

    @Column
    private String coverSerialNo1;

    @Column
    private String coverSerialNo3;

    @Column
    private String terminalSerialNo;








}
