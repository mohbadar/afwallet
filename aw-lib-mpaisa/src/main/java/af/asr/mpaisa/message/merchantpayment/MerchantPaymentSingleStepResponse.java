package af.asr.mpaisa.message.merchantpayment;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Merchant Payment (Single Step)
 * Merchant Payment is the process in which the customer can buy goods with the M-Paisa
 * points from his wallet through a channel user
 *
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE> CMPRRESP</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS >
 <MESSAGE>Merchant Payment Success by 7754542454. The details are as follows:
 transaction amount: 1.00 INR, charges: 1.00 INR, commission: 0.0 INR, transaction Id:
 MP110517.1416.C00001, new balance : 24.00 INR.</MESSAGE>
 <IVR-RESPONSE> 9014#7761626364|5.0000|1.1000|1.0500|
 MP121101.1613.C00003|25.9087#
 </IVR-RESPONSE>
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
public class MerchantPaymentSingleStepResponse {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private String MESSAGE;
    @XmlElement(name = "IVR-RESPONSE")
    private String IVRRESPONSE;
    @XmlElement
    private int TRID;

    /**
     * Tag Fields Example Max
     * Lengt
     * h Optional/
     * Mandatory Remarks
     * TYPE RMPRRESP RMPRRESP 10 M Response Type
     * TXNID <Transaction ID> MT070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Merchant Payment
     * Transaction.
     * Used for informative purpose.
     * TXNSTATUS <Transaction Status>
     * TRID Transfer ID
     * IVR-
     * RESPONSE IVR-RESPONSE
     * 4.4.
     * 200
     * 5
     * M
     * Various transaction status
     * Failed, Success etc
     * Transfer ID
     * 9014#7761626364|
     * 5.0000|1.1000|1.05
     * 00|MP121101.1613
     * .C00003|25.9087#
     * M
     */
}
