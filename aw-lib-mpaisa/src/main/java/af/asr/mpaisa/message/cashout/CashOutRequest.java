package af.asr.mpaisa.message.cashout;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Cash Out transaction is the process in which the customer can withdraw the M-Paisa points from his
 * wallet through a channel user. The channel user initiates the transaction and the customer must
 * confirm the transfer by entering his PIN to complete the transaction.
 *
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>RCOREQ</TYPE>
 * <MSISDN><Sender MSISDN></MSISDN>
 * <MSISDN2><Receiver MSISDN></MSISDN2>
 * <PROVIDER><Provider ID></PROVIDER>
 * <PAYID><Wallet type></PAYID>
 * <PROVIDER2><Provider ID></PROVIDER2>
 * <PAYID2><Wallet type></PAYID2>
 * <AMOUNT><Amount></AMOUNT>
 * <IDNO><ID Number></IDNO>
 * <PIN><Sender PIN></PIN>
 * <LANGUAGE1><Payer Language></LANGUAGE1>
 * <LANGUAGE2><Payee Language></LANGUAGE2>
 * </COMMAND>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class CashOutRequest {

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
    private long IDNO;
    @XmlElement
    private int PIN;
    @XmlElement
    private int LANGUAGE1;
    @XmlElement
    private int LANGUAGE2;

    /**
     * Tag Fields Example Max
     * Length O
     * -
     * Optional/
     * M
     * –
     * Mandatory Remarks
     * TYPE RCOREQ RCOREQ 10 M Request Type
     * MSISDN <Sender MSISDN> 9942222 15 M Sender MSISDN
     * should be without
     * country code.
     * MSISDN2 <Receiver MSISDN> 9942222 15 M Receiver MSISDN
     * should be without
     * country code.
     * AMOUNT <Amount> 100 10 M Numeric Only.
     * Should accept
     * IDNO <ID Number > 9942223 15 O Numeric Only.
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
     * system,
     * PROVIDER < PROVIDER> 101 M
     * PAYID < PAY ID> 12 M
     * Provider idM
     * PROVIDER2 < PROVIDER2> 101 M
     * PAYID2 < PAY ID2> 12 M
     * Provide
     */
}
