package af.gov.anar.ebreshna.configuration.payment.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.payment.enumeration.TransactionType;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "payment_third_party_payment", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class ThirdPartyPayment extends BaseEntity {

    @Column
    private String vendorName;

    @Column
    private String vendorAddress;

    @Column
    private String primaryName;

    @Column
    private String primaryPhone;

    @Column
    private String primaryEmail;

    @Column
    private String primaryAddress;


    @Column
    private String secondaryName;

    @Column
    private String secondaryEmail;

    @Column
    private String secondaryPhone;

    @Column
    private String secondaryAddress;

    @Column
    private String prnoPrefix;

    @Column
    private boolean customerPayLimitApplicable;

    @Column
    private boolean vendorMaxLimitApplicable;

    @Column
    private double vendorMaxLimit;

    @Column
    private double customerPayLimit;

    @Column
    private Date contractStartDate;

    @Column
    private Date contractExpireDate;

    @Column
    private String contractDocument;


    @Getter
    private TransactionType transactionType;






}
