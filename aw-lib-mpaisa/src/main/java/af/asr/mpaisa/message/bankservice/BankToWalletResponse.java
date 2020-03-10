package af.asr.mpaisa.message.bankservice;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bank To wallet
 * Customer can do cash-in from his bank a/c into his wallet.
 Response XML
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE>CBWRESP</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS>
 <MESSAGE>Your bank to wallet account transfer is done.</MESSAGE>
 </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankToWalletResponse {

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
     * TYPE CWBRESP CWBRESP 10 M Response Type
     * TXNID <Transaction ID> XX121126.1821.C00002 20 M M-Paisa Transaction ID
     * for the Bill Viewing
     * Transaction. Used for
     * informative purpose.
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction status
     * Failed, Success etc
     */

}
