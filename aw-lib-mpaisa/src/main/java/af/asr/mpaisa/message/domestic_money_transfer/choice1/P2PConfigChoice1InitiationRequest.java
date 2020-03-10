package af.asr.mpaisa.message.domestic_money_transfer.choice1;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Subscribers can transfer points to each other using the domestic money transfer service.
 * P2P Services Options
 * In M-Paisa system there will be following Options available for P2P Money transfer service.
 *
 * This is a normal P2P transaction. It is also known as “Always Confirmation Required” mode of
 * Domestic Money transfer. The receiver user must always confirm this transaction in order for
 * the transfer to complete.
 *
 *
 * Request XML (Initiation)
 * <?xml version="1.0"?>M
 * <COMMAND>
 * <TYPE>CTMREQ</TYPE>
 * <MSISDN><Payer MSISDN></MSISDN>
 * <PIN><Payer PIN></PIN>
 * <PROVIDER><Provider ID></PROVIDER>
 * <PAYID><Wallet type></PAYID>
 * <PROVIDER2><Provider ID></PROVIDER2>
 * <PAYID2><Wallet type></PAYID2>
 * <MSISDN2><Payee MSISDN></MSISDN2>
 * <AMOUNT><Amount></AMOUNT>
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
public class P2PConfigChoice1InitiationRequest {

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
    private int PROVIDER2;
    @XmlElement
    private int PAYID2;
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
     * Optional/
     * M
     * –
     * Mandatory Remarks
     * TYPE CTMREQ CTMREQ 10 M Request Type
     * MSISDN <Payer MSISDN> 9942222 15 M Payer MSISDN should
     * be without country
     * code.
     * PROVIDER < PROVIDER> 101 M Provider id
     * PAYID < PAY ID> 12 M PROVIDER2 < PROVIDER> 101 M PAYID2 < PAY ID> 12 M PIN <Payer PIN> 3946 4 M Numeric Only.
     * MSISDN2 <Payee MSISDN> 9942222 15 M Payee MSISDN
     * should be without
     * country code.
     * AMOUNT <Amount> 100 10 M Numeric Only.
     * Should accept
     * LANGUAGE1 <Payer Language> 1 O Numeric only Payer
     * Language Code
     * Provider id
     * This code must be
     * defined in M-Paisa
     * system,
     * LANGUAGE2
     * <Payee Language>
     * 1
     * O
     * Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system,
     */
}
