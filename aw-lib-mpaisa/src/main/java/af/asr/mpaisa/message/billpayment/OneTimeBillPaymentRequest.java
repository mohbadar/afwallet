package af.asr.mpaisa.message.billpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Pay Bill Payment (From Wallet)  One  Time/Prepaid  Bill
 * Pre-paid transactions generally means in which either a bill is not present in the system or in cases
 * where there is no bills at all in the system like in case of Insurance Premium Payments or Cable TV
 * top up’s.
 *
 *
 * Request XML
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>CPMBREQ</TYPE>
 * <MSISDN><Payer MSISDN></MSISDN>
 * <PIN><Payer PIN></PIN>
 * <PROVIDER><Provider ID></PROVIDER>
 * <BPROVIDER><Provider ID></BPROVIDER>
 * <PAYMENT_INSTRUMENT><Payment instrument></PAYMENT_INSTRUMENT>
 * <PAYID><Wallet type></PAYID>
 * <ACCNO><Accno></ACCNO>
 * <BILLCCODE><Bill Company Code></BILLCCODE>
 * <BILLANO><Account number With Company ></BILLANO>
 * <AMOUNT><Amount to be paid ></AMOUNT>
 * <LANGUAGE1><Payer Language></LANGUAGE1>
 * </COMMAND>
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class OneTimeBillPaymentRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private int PIN;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private int BPROVIDER;
    /**
     * Should be ‘WALLET’
     * for wallet initiated
     * bill payment/’BANK’
     * for bill payment
     * initiated by
     * subscriber’s bank
     * account
     */
    @XmlElement
    private String PAYMENT_INSTRUMENT;
    @XmlElement
    private int PAYID;
    @XmlElement
    private String ACCNO;
    @XmlElement
    private int BILLCCODE;
    @XmlElement
    private String BILLANO;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private int LANGUAGE1;


    /**
     * TAG
     * Fields
     * Remarks
     * Example
     * Field Type
     * Optional/
     * Mandatory
     * TYPE CPMBREQ CPMBREQ 10 M Request Type
     * MSISDN <Payer MSISDN> 9942222 15 M Payer MSISDN should
     * be without country
     * code.
     * PIN <Payer PIN> 3946 4 M Numeric Only.
     * default “0000” will
     * be considered if not
     * specified
     * BILLCCODE < Bill Company Code > 9942223 15 M Numeric Only.
     * BILLANO < Account number With
     * Company > 9942222 15 M Can Be Alpha-
     * Numeric
     * AMOUNT <Amount> 100 10 M Numeric Only.
     * Should accept
     * LANGUAGE1 <Payer Language> 0 1 O (Tag is
     * mandatory) Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system.
     * ACCNO
     * <Accno>
     * 45892132131
     * 16
     * O/M
     * Accno is account of
     * subscriber which is
     * mandatory in case of
     * ‘BANK’ initiated billM
     * payment.
     * PAYMENT_INSTR
     * UMENT <
     * PROVIDER PAYID WALLET/BANK M Should be ‘WALLET’
     * for wallet initiated
     * bill payment/’BANK’
     * for bill payment
     * initiated by
     * subscriber’s bank
     * account
     * < PROVIDER> 101 M Provider id
     * < PAY ID> 12 M 100 M
     * PAYMENT_INSTRUM
     * ENT >
     */

}
