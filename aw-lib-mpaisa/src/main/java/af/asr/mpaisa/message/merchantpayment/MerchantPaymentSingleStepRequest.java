package af.asr.mpaisa.message.merchantpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Merchant Payment (Single Step)
 * Merchant Payment is the process in which the customer can buy goods with the M-Paisa
 * points from his wallet through a channel user
 *
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>CMPREQ</TYPE>
 * <MSISDN>776767676</MSISDN>M
 * <MERCODE>774545454</MERCODE>
 * <PROVIDER><Provider ID></PROVIDER>
 * <PAYID><Wallet type></PAYID>
 * <PROVIDER2><Provider ID></PROVIDER2>
 * <PAYID2><Wallet type></PAYID2>
 * <AMOUNT>1000</AMOUNT>
 * <PIN>2468</PIN>
 * <LANGUAGE1>1</LANGUAGE1>
 * <LANGUAGE2>1</LANGUAGE2>
 * </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchantPaymentSingleStepRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private String MERCODE;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private int PAYID;
    @XmlElement
    private int PROVIDER2;
    @XmlElement
    private int PAYID2;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private int PIN;
    @XmlElement
    private int LANGUAGE1;
    @XmlElement
    private int LANGUAGE2;

    /**
     * Tag Fields Example Max Length O -Optional/M –
     * Mandatory Remarks
     * TYPE CMPREQ CMPREQ 10 M Request Type
     * MSISDN <Sender MSISDN> 9942222 15 M Sender MSISDN
     * should be without
     * country code.
     * MERCODE <Merchant Code> 998765 M MSISDN of the
     * merchant
     * AMOUNT <Amount> 100 10 M Numeric Only.
     * Should accept
     * PIN <Sender PIN> 3946 4 M Numeric Only.
     * LANGUAGE1 <Sender Language> 0 1 O (Tag is
     * mandatory) Numeric only Sender
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system,
     * PROVIDER < PROVIDER> 101 M
     * PAYID < PAY ID> 12 M
     * PROVIDER2 < PROVIDER> 101 M
     * PAYID2 < PAY ID> 12 M
     * Provider id
     */
}
