package af.asr.mpaisa.message.merchantpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Merchant Payment (Initiate)
 * Merchant Payment is the process in which the customer can buy goods with the M-Paisa points from
 * his wallet through a channel user. The channel user initiates the transaction and the customer must
 * confirm the transfer by entering his PIN to complete the transaction.
 *
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>RMPREQ</TYPE>
 * <MSISDN><Sender MSISDN></MSISDN>
 * <MSISDN2><Receiver MSISDN></MSISDN2>
 * <PROVIDER><Provider ID></PROVIDER>
 * <PAYID><Wallet type></PAYID>
 * <PROVIDER2><Provider ID></PROVIDER2>
 * <PAYID2><Wallet type></PAYID2>
 * <AMOUNT><Amount></AMOUNT>
 * <PIN><Sender PIN></PIN>
 * <LANGUAGE1><Sender Language></LANGUAGE1>
 * <LANGUAGE2><Receiver Language></LANGUAGE2>
 * </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchantPaymentRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private String MSISDN2;
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
     * TYPE RMPREQ RMPREQ 10 M Request Type
     * MSISDN <Sender MSISDN> 9942222 15 M Sender MSISDN
     * should be without
     * country code.
     * MSISDN2 <Receiver MSISDN> 9942222 15 M Receiver MSISDN
     * should be without
     * country code.
     * AMOUNT <Amount> 100 10 M Numeric Only.
     * Should accept
     * PIN <Sender PIN> 3946 4 M Numeric Only.
     * LANGUAGE1 <Sender Language> 0 1 O (Tag is
     * mandatory) Numeric only Sender
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system,
     * LANGUAGE2
     * <Receiver Language>
     * 0
     * 1
     * O (Tag is
     * mandatory)
     * Numeric only
     * Receiver Language
     * Code
     * This code must be
     * defined in M-Paisa
     * system.
     * PROVIDER < PROVIDER> 101 M
     * PAYID < PAY ID> 12 M
     * PROVIDER2 < PROVIDER> 101 M
     * PAYID2 < PAY ID> 12 M
     */

}
