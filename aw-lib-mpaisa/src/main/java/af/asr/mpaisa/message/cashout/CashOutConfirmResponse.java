package af.asr.mpaisa.message.cashout;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <COMMAND>
 * <TYPE> RCORESP</TYPE>
 * <TXNID><Transaction ID></ TXNID>
 * <TXNSTATUS><Transaction Status></ TXNSTATUS >
 * <MESSAGE>Cash Out success by The details are as follows: transaction amount: 1.00 INR,
 * charges: 1.00 INR, commission: 0.0 INR, transaction Id: CO110516.0028.C00003, net debit
 * amount 2.00 INR, new balance: 4.00 INR.</MESSAGE>
 * <TRID><TRID></TRID>
 * </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class CashOutConfirmResponse {

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
     * TYPE RCORESP RCORESP 10 M Response Type
     * TXNID <Transaction ID> CO070608.1512.000001 20 M M-Paisa Transaction ID for the
     * Cash-Out Transaction. Used for
     * informative purpose.
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction status
     * Failed, Success etc
     * TRID <Transfer ID
     * M
     */
}
