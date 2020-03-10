package af.asr.mpaisa.message.bankservice;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;


/**
 * Wallet to Bank
 * Customer can transfer money points from his own wallet to his registered bank a/c in M-Paisa.
 * Request XML
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>CWBREQ</TYPE>
 * <MSISDN><Subs MSISDN></MSISDN>
 * <MSISDN2><MSISDN></MSISDN2>
 * <ACCNO2><Subs Account number ></ACCNO2>
 * <AMOUNT><Amount></AMOUNT>
 * <PROVIDER><Provider ID></PROVIDER>
 * <PAYID><Wallet type></PAYID>
 * <PROVIDER2><Provider ID></PROVIDER2>
 * <BANKID><Bank id></BANKID>
 * <PIN><Pin></PIN>
 * <LANGUAGE1>1</LANGUAGE1>
 * </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class WalletToBankRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private String MSISDN2;
    @XmlElement
    private String ACCNO2;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private int PAYID;
    @XmlElement
    private int PROVIDER2;
    @XmlElement
    private String BANKID;
    @XmlElement
    private int PIN;
    @XmlElement
    private int LANGUAGE1;

    /**
     * Tag Fields Example Max
     * Length O
     * -
     * Optional/
     * M
     * –
     * Mandatory Remarks
     * TYPE CWBREQ CWBREQ 10 M Request Type
     * MSISDN <Subs MSISDN> 9942222 15 M MSISDN should be
     * without country
     * code.
     * MSISDN2 <Payee MSISDN> 9942223 15 M Numeric Only. Not
     * used in current
     * version . It will have
     * same value as
     * MSISDN.
     * ACCNO2 <Subs Account number > 9942222 15 M Numeric Only.
     * AMOUNT <Amount> 7637463 15 M Numeric Only.
     * Should accept
     * PIN <Payer PIN> 3946 4 M Numeric Only.
     * LANGUAGE1 <Payer Language> 0 1 O (Tag is
     * mandatory) Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system.
     * PROVIDER < PROVIDER> 101 M
     * PAYID < PAY ID> 12 M
     * PROVIDER2 < PROVIDER> 101 M
     * Provider id
     * Provider idM
     * BANKID
     * <Bankid>
     * M
     * Bank id
     */
}
