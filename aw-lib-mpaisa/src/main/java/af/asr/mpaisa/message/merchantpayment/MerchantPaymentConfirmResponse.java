package af.asr.mpaisa.message.merchantpayment;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE>RMPRESP</TYPE>
 <TXNID><Transaction ID></ TXNID>
 <TXNSTATUS><Transaction Status></ TXNSTATUS >
 <TXNSTATUS>200</TXNSTATUS>
 <MESSAGE>Merchant Payment Success by <Receiver MSISDN>. The details are as follows:
 transaction amount: 1.00 INR, charges: 1.00 INR, commission: 0.0 INR, transaction Id:
 MP110516.0213.C00007, new balance : 2.00 INR.</MESSAGE>
 </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchantPaymentConfirmResponse {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private String MESSAGE;


    /**
     * Tag Fields Example Max
     * Lengt
     * h Optional/
     * Mandatory Remarks
     * TYPE RMPRESP RMPRESP 10 M Response Type
     * TXNID <Transaction ID> BP070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Merchant Payment
     * Transaction. Used for
     * informative purpose.
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction status
     * Failed, Success etc
     */
}
