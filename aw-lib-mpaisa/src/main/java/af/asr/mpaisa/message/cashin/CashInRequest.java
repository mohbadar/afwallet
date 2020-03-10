package af.asr.mpaisa.message.cashin;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * <COMMAND>
     * <TYPE>RCIREQ</TYPE>
     * <MSISDN><Payer MSISDN></MSISDN>
     * <MSISDN2><Payee MSISDN></MSISDN2>
     * <AMOUNT><Amount></AMOUNT>
     * <IDNO><ID Number></IDNO>
     * <PIN><Payer PIN></PIN>
     * <SNDPROVIDER>101</SNDPROVIDER>
     * <RCVPROVIDER>101</RCVPROVIDER>
     * <SNDINSTRUMENT>11</SNDINSTRUMENT>
     * <RCVINSTRUMENT>11</RCVINSTRUMENT>
     * <LANGUAGE1><Payer Language></LANGUAGE1>
     * <LANGUAGE2><Payee Language></LANGUAGE2>
 * </COMMAND>
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class CashInRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private String MSISDN2;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private String IDNO;
    @XmlElement
    private int PIN;
    @XmlElement
    private int SNDPROVIDER;
    @XmlElement
    private int RCVPROVIDER;
    @XmlElement
    private int SNDINSTRUMENT;
    @XmlElement
    private int RCVINSTRUMENT;
    @XmlElement
    private int LANGUAGE1;
    @XmlElement
    private int LANGUAGE2;

    /**
     *
     *
     * Tag Fields Example Max
     * Length O
     * -
     * Optional/
     * M
     * –
     * Mandatory Remarks
     * TYPE RCIREQ RCIREQ 10 M Request Type
     * MSISDN <Payer MSISDN> 9942222 15 M Payer MSISDN should
     * be without country
     * code.
     * MSISDN2 <Payee MSISDN> 9942222 15 M Payee MSISDN
     * should be without
     * country code.
     * SNDPROVIDER < PROVIDER> 101 M Provider id
     * SNDINSTRUMEN
     * T < PAY ID> 12 M Wallet type id of sender
     *
     * RCVPROVIDER < PROVIDER> 101 M Provider id
     * RCVINSTRUMENT < PAY ID> 12 M Wallet type id of
     * receiver
     * AMOUNT <Amount> 100 10 M Numeric Only.
     * Should accept
     * IDNO <ID Number > 9942223 15 O (Tag is
     * mandatory) alphaNumeric Only.
     * PIN <Payer PIN> 3946 4 M Numeric Only.
     * LANGUAGE1 <Payer Language> 0 1 O (Tag is
     * mandatory) Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system.
     *
     *
     */
}
