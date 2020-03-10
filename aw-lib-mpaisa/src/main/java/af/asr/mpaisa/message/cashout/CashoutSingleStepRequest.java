package af.asr.mpaisa.message.cashout;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Through Cash Out (Single Step) a M-Paisa customer can withdraw M-Paisa points from his
 * wallet. It is a single step transaction and is initiated by customer.
 *
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>CCOREQ</TYPE>
 * <MSISDN><Sender MSISDN></MSISDN>
 * <AGNTCODE><Agent code></AGNTCODE>
 * <AMOUNT><Txn Amount></AMOUNT>
 * <PROVIDER><Provider ID></PROVIDER>
 * <PAYID><Wallet type></PAYID>
 * <PROVIDER2><Provider ID></PROVIDER2>
 * <PAYID2><Wallet type></PAYID2>
 * <PIN><Sender PIN></PIN>
 * <LANGUAGE1><Sender Language></LANGUAGE1>
 * <LANGUAGE2><Receiver Language></LANGUAGE2>
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
public class CashoutSingleStepRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private  String AGNTCODE;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private int PAYID;
    @XmlElement
    private int PROVIDER2;
    @XmlElement
    private int PAYID2;
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
     * TYPE CCOREQ CCOREQ 10 M Request Type
     * MSISDN <Sender MSISDN> 9942222 15 M Sender MSISDN
     * should be without
     * country code.
     * AGNTCODE <Agent code> 1023 15 M Agent Code of
     * Retailer
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
     * system,
     * PROVIDER
     * < PROVIDER>
     * 101
     * M
     * Provider idM
     * PAYID < PAY ID> 12 M
     * PROVIDER2 < PROVIDER2> 101 M
     * PAYID2 < PAY ID2> 12 M
     * Pro
     */
}
