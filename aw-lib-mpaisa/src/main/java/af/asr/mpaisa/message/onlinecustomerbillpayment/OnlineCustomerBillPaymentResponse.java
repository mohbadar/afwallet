package af.asr.mpaisa.message.onlinecustomerbillpayment;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 Response Xml
 <COMMAND>
 <TYPE>RCPMBRESP</TYPE>
 <TXNID>txnId</TXNID>M
 <TXNSTATUS>200</TXNSTATUS>
 <OPT1></OPT1>
 <OPT2></OPT2>
 <MESSAGE><message> </MESSAGE>
 </COMMAND>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class OnlineCustomerBillPaymentResponse {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private String OPT1;
    @XmlElement
    private String OPT2;
    @XmlElement
    private String MESSAGE;


    /**
     * Tag Fields Example Max
     * Lengt
     * h Optional/
     * Mandatory Remarks
     * TYPE CPMBRESP RCPMBRESP 10 M Response Type
     * TXNID <Transaction ID> BP070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Bill Payment Transaction.
     * Used for informative purpose.
     * TXNSTATUS <Transaction Status> 200 10 M Various transaction status
     * Failed, Success etc
     * OPT1 Optional Field 1 20 O OPT2 Optional Field 2 20 O MESSAGE <message>
     * M-Paisa
     * MESSAGE
     * M
     * Response Details
     */
}
