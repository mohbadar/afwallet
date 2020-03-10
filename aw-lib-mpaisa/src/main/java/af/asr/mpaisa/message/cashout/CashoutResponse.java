package af.asr.mpaisa.message.cashout;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Cash Out transaction is the process in which the customer can withdraw the M-Paisa points from his
 * wallet through a channel user. The channel user initiates the transaction and the customer must
 * confirm the transfer by entering his PIN to complete the transaction.
 *
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE> RCORESP </TYPE>
 * <TXNID><Transaction ID></TXNID>
 * <TXNSTATUS><Transaction Status></TXNSTATUS >
 * <MESSAGE>Cash Out transaction is initiated successfully. Confirmation is sent to receiver.
 * </MESSAGE>
 * </COMMAND>
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class CashoutResponse {

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
     * TYPE RCORESP RCORESP 10 M Response Type
     * TXNID <Transaction ID> CO070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Cash-Out Transaction.
     * Used for informative purpose.
     * TXNSTATUS
     * <Transaction Status>
     * 200
     * 5
     * M
     * Various transaction sta
     */
}
