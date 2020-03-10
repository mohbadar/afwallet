package af.asr.mpaisa.message.bankservice;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wallet to Bank
 * Customer can transfer money points from his own wallet to his registered bank a/c in M-Paisa.
 Response XML
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE>CWBRESP</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS>
 <MESSAGE>Your wallet to bank account transfer is done.</MESSAGE>
 </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class WalletToBankResponse {

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
