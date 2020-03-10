package af.asr.mpaisa.message.billpayment;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE> RCPMBRESP</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS>
 <OPT1><Optional Field 1></OPT1>
 <OPT2><Optional Field 2></OPT2>
 </COMMAND>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class OnlineBillPaymentResponse {

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
     * OPT1 Optional Field 1 20 O OPT2 Optional Field 2 20 O
     */
}
