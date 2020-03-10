package af.asr.mpaisa.message.bankservice;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Bank To wallet
 * Customer can do cash-in from his bank a/c into his wallet.
 * Request XML
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>CBWREQ</TYPE>
 * <MSISDN><Subs MSISDN></MSISDN>
 * <MSISDN2><MSISDN></MSISDN2>
 * <ACCNO><Subs Account number ></ACCNO>
 * <PROVIDER><Provider ID></PROVIDER>
 * <BANKID><BANK id></BANKID>
 * <PAYID2><Wallet type></PAYID2>
 * <PROVIDER2><Provider ID></PROVIDER2>
 * <AMOUNT><Amount></AMOUNT>
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
public class BankToWalletRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private String MSISDN2;
    @XmlElement
    private String ACCNO;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private String BANKID;
    @XmlElement
    private int PAYID2;
    @XmlElement
    private int PROVIDER2;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private int PIN;
    @XmlElement
    private int LANGUAGE;


    /**
     * TYPE CBWREQ CBWREQ 10 M Request Type
     * MSISDN < MSISDN> 9942222 15 M MSISDN should be
     * without country
     * code.
     * MSISDN2 <Payee MSISDN> 9942223 15 M Numeric Only. Not
     * used in current
     * version . It will have
     * same value as
     * MSISDN.
     * ACCNO <Subs Account number > 9942222 15 M Numeric Only.
     * AMOUNT <Amount> 7637463 15 M Numeric Only.
     * Numeric Only.
     * Should accept
     * PIN <Payer PIN> 3946 4 M Numeric Only.
     * LANGUAGE1 <Payer Language> 0 1 O (Tag is
     * mandatory) Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system.
     * PROVIDER < PROVIDER> 101 M
     * Provider id
     * PAYID2 < PAY ID> 12 M PROVIDER2 < PROVIDER> 101 M Provider id
     * Bankid <Bankid> M Bankid
     */

}
