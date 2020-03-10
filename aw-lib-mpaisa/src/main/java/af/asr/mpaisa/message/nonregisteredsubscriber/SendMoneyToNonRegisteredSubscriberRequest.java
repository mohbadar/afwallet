package af.asr.mpaisa.message.nonregisteredsubscriber;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * To send money from wallet to non-registered subscriber
 * Request XML
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>RNMCOREQ</TYPE>
 * <MSISDN><Sender MSISDN></MSISDN>
 * <PIN><Sender Pin></PIN>
 * <PROVIDER><Provider ID></PROVIDER>
 * <PAYID><Wallet type></PAYID>
 * <MSISDN2><MSISDN used for contact and number></MSISDN2>
 * <AMOUNT><Amount></AMOUNT>
 * <LANGUAGE1>1</LANGUAGE1>
 * <LANGUAGE2>2</LANGUAGE2>
 * </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class SendMoneyToNonRegisteredSubscriberRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private int PIN;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private int PAYID;
    @XmlElement
    private String MSISDN2;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private int LANGUAGE1;
    @XmlElement
    private int LANGUAGE2;


    /**
     * Tag Fields Example Max
     * Length O
     * -
     * Optional/M
     * –Mandatory Remarks
     * TYPE RNMCOREQ RNMCOREQ 10 M Request Type
     * MSISDN <Sender MSISDN> 9942222 15 M MSISDN should be
     * without country
     * code.
     * MSISDN2 <Receiver MSISDN> 9942223 15 M Numeric Only.
     * AMOUNT <Amount> 7637463 15 M Numeric Only.
     * Numeric Only.
     * Should accept
     * PIN <Sender PIN> 3946 4 M Numeric Only.
     * LANGUAGE1 < Language> 0 1 O (Tag is
     * mandatory) Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system.
     * LANGUAGE2 <Default Language> 1
     * PROVIDER < PROVIDER> PAYID < PAY ID>
     * 1
     * O (Tag is
     * mandatory) The Default Language
     * Code
     * 101 M Provider id
     * 12 M
     */

}
