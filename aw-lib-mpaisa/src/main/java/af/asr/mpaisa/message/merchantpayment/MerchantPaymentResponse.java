package af.asr.mpaisa.message.merchantpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Merchant Payment (Initiate)
 * Merchant Payment is the process in which the customer can buy goods with the M-Paisa points from
 * his wallet through a channel user. The channel user initiates the transaction and the customer must
 * confirm the transfer by entering his PIN to complete the transaction.
 *
 * <?xml version="1.0"?>
 <COMMAND>
 <TYPE> RMPRESP </TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS >
 <MESSAGE>Payment done. The customer will have to confirm the payment by entering his
 PIN and then you will receive an SMS. Thank you for using M-Paisa service.</MESSAGE>
 <TRID><Transfer ID></TRID>
 </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchantPaymentResponse {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private String MESSAGE;
    @XmlElement
    private int TRID;

    /**
     * Tag Fields Example Max
     * Lengt
     * h Optional/
     * Mandatory Remarks
     * TYPE RMPRESP RMPRESP 10 M Response Type
     * TXNID <Transaction ID> MT070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Merchant Payment
     * Transaction.
     * Used for informative purpose.
     * TXNSTATUS <Transaction Status>
     * TRID Transfer ID
     * 200
     * 5
     * M
     * Various transaction status
     * Failed, Success etc
     * Transfer ID
     */

}
