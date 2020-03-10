package af.asr.mpaisa.message.merchantpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>RMPRREQ</TYPE>
 * <MSISDN><Recipient MSISDN></MSISDN>
 * <PROVIDER><SUBSCRIBER PROVIDER></ PROVIDER >
 * <PIN><Receiver PIN></PIN>
 * <TXNID><Transaction Id></TXNID>
 * <STATUS><Status></STATUS>
 * <LANGUAGE1><Payer Language></LANGUAGE1>
 * </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchantPaymentConfirmRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private int PIN;
    @XmlElement
    private int TXNID;
    @XmlElement
    private int STATUS;
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
     * TYPE RMPREQ RMPREQ 10 M Request Type
     * MSISDN <Receiver MSISDN> 9942222 15 M Receiver MSISDN
     * should be without
     * country code.
     * PIN <Sender PIN> 3946 4 M Numeric Only.
     * TXNID <Transaction ID> MT070608.1512.000
     * 001 20 M M-Paisa Transaction
     * ID for the Transfer
     * M- money
     * Transaction.
     * Used for informativeM
     * purpose.
     * LANGUAGE1
     * <Language>
     * 0
     * 1
     * O (Tag is
     * mandatory)
     * Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system
     * STATUS <Status>
     * PROVIDER <PROVIDER>
     * 0/1
     * O This field would be
     * used when end
     * subscriber has either
     * cancelled or has not
     * responded (session
     * expire) to the
     * request 0- approve &
     * 1- cancel
     * M SUBSCRIBER
     * PROVIDER
     */
}
