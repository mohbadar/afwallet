package af.asr.mpaisa.message.nonregisteredsubscriber;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * To send money from wallet to non-registered subscriber
 Response XML
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE> RNMCORES</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS>
 <TRID>Transfer ID</TRID>
 </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class SendMoneyToNonRegisteredSubscriberResponse {


    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private int TRID;

    /**
     * Tag
     * Fields
     * Example
     * Max
     * Lengt
     * h
     * Optional/
     * Mandatory Remarks
     * h
     * TYPE CTMRRESP CTMRRESP 10 M Response Type
     * TXNID <Transaction ID> XX121126.1821.C00002 20 M M-Paisa Transaction ID
     * for the Bill Viewing
     * Transaction. Used for
     * informative purpose.
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction statusM
     * Failed, Success etc
     * <TRID>
     * <Transfer ID>
     * Transfer id
     */

}
