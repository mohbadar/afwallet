package af.gov.anar.ebreshna.metering.reading_entry;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.metering.observation.ObservationMaster;
import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "metering_meter_reading_entry")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
/**
 * Contains Meter Details of All Consumers
 */
public class MeterReadingEntry extends BaseEntity {

    @OneToOne(targetEntity = Applicant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "applicant_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Applicant applicant;

    @Column
    private Date presentReadingDate;

    @Column
    private String presentKwhStatus;

    @Column
    private String presentKvaStatus;

    @Column
    private String presentKvahStatus;

    @Column
    private String presentReadingKwh;

    @Column
    private String presentReadingKva;

    @Column
    private String presentReadingKvah;

    @Column
    private Date previousReadingDate;

    @Column
    private String previousKwhStatus;

    @Column
    private String previousKvaStatus;

    @Column
    private String previousKvahStatus;

    @Column
    private String previousReadingKwh;

    @Column
    private String previousReadingKva;

    @Column
    private String previousReadingKvah;


    @Column
    private String multiplicationFactorForKwh;

    @Column
    private String multiplicationFactorForKva;

    @Column
    private String multiplicationFactorForKvah;

    @Column
    private String recordedKwh;

    @Column
    private String recordedKva;

    @Column
    private String recordedKvah;

    @Column
    private String recordCreatedBy;

    @Column
    private String recordCreateDate;

    @Column
    private String recordUpdatedBy;

    @Column
    private String recordUpdatedDate;

    @Column
    private int readingMonth;

    @Column
    private int readingYear;

    @OneToOne(targetEntity = ObservationMaster.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "observation_master_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private ObservationMaster observationMaster;


    /**
     * RECOMMENDED UNITS FOR KWH
     */
    @Column
    private String recommendedUnitKwh;

    @Column
    private String recommendedUnitKva;

    @Column
    private String recommendedUnitKvah;

    /**
     * METER STATUS DATE WHEN IT IS NOT NORMAL
     */
    private String meterStatusDateWhenAbnormal;

    @Column
    private boolean readingUsedforBilling;

    @Column
    private double averageUnitForKwh;

    @Column
    private double averageUnitForKva;

    @Column
    private double averageUnitForKvah;

    @Column
    private String remark;

    @Column
    private double behaviourKwh;

    @Column
    private double behaviourKva;

    @Column
    private  double behaviourKvah;



    /**

     * MF_KWH
     * MF_KVA
     * MF_KVAH
     * DF_KWH
     * DF_KVA
     * DF_KVAH
     * RECORDED_KWH
     * RECORDED_KVA
     * RECORDED_KVAH
     * CREATED_BY
     * CREATE_DATE
     * UPDATED_BY
     * UPDATE_DATE
     * DB_IP_ADDRESS
     * SOURCE_OFFICE_ID
     * TARGET_OFFICE_ID
     * RECORD_STATUS
     * SCREEN_ID
     * MONTH
     * YEAR
     * FLAG1
     * FLAG2
     * FLAG3
     * FLAG4
     * FLAG5
     * MODULE_ID
     * SUBMODULE_ID
     * COMM_MODE_ID
     * MTR_RDR_ID
     * MTR_CHG_DT
     * NORMAL_STATUS_DT
     * OBSERVATION_ID
     * RDG_MONTH
     * RDG_YEAR
     * RECOMM_KWH
     * RECOMM_KVA
     * RECOMM_KVAH
     * STATUS_DATE
     * RDG_USE
     * AVG_UNITS_KWH
     * AVG_UNITS_KVA
     * AVG_UNITS_KVAH
     * EMPLOYEE_ID
     * REMARKS
     * BEHAVIOUR_KWH
     * BEHAVIOUR_KVA
     * BEHAVIOUR_KVAH
     * TOD_ID
     * PREV_TOD_VOLTAGE
     * PREV_TOD_CURRENT
     * PREV_TOD_PF
     * PRES_TOD_VOLTAGE
     * PRES_TOD_CURRENT
     * PRES_TOD_PF
     * REC_TOD_VOLTAGE
     * REC_TOD_CURRENT
     * PREV_BD_KVA
     * PRST_BD_KVA
     * PROV_FLAG
     * CHECK_CONDITION
     * METERNO
     * BILL_REC_KWH
     * DELAYED_READING
     * OFFICE_ID
     * CURR_CAT
     * CURR_PHASE
     * LOAD_TYPE
     * ANOMALIES
     * NEW_MTRNO
     * PROVISIONAL
     * CYCLE
     * ROUTE_CODE
     * ROUTE_CODE_DHARI
     * ANOMALIES_PREV
     * OLDPRST_READING
     * OLDPRST_STATUS
     * RDG_UPDATED_BY
     * DHARI_YEAR
     */
}
