package af.asr.mpaisa.message.cashout;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Cash Out Confirm (Two Step)
 * Initiate is step 2 of two step transactions.
 *
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>RCORREQ</TYPE>
 * <MSISDN><Recipient MSISDN></MSISDN>
 * <PROVIDER><SUBSCRIBER PROVIDER></ PROVIDER >
 * <PIN><Receiver PIN></PIN>
 * <TXNID><Transaction Id></TXNID>
 * <LANGUAGE1><Receiver Language></LANGUAGE1>
 * </COMMAND>
 *
 */

@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CashOutConfirmRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private String PROVIDER;
    @XmlElement
    private int PIN;
    @XmlElement
    private String TXNID;
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
     * TYPE RCOREQ RCOREQ 10 M Request Type
     * MSISDN <Receiver MSISDN> 9942222 15 M Receiver MSISDN
     * should be withoutM
     * country code.
     * PIN <Receiver PIN> 3946 4 M Numeric Only.
     * TXNID <Transaction ID> CO070608.1512.0000
     * 01 20 M M-Paisa
     * Transaction ID..
     * LANGUAGE <Language> 0 1 O (Tag is
     * mandatory) Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system,
     * PROVIDER
     * <PROVIDER>
     * M
     * SUBSCRIBER
     * PROVIDER
     */
}
