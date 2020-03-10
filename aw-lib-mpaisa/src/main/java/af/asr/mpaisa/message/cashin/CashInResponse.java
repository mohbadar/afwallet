package af.asr.mpaisa.message.cashin;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <COMMAND>
 * <TYPE> RCIRESP</TYPE>
 * <TXNID><Transaction ID></TXNID>
 * <TXNSTATUS><Transaction Status></TXNSTATUS>
 * <TRID><Transfer ID></TRID>
 * <MESSAGE>Cash In Success to <Payee MSISDN>. The details are as follows: transaction amount:
 * <Amount>, transaction Id: <Transaction ID>, charges: 1.00 INR, commission: 0.0 INR, net debit
 * amount : 6.00 INR, new balance: 88.00 INR.</MESSAGE>
 * </COMMAND>
 */
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CashInResponse {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private String TRID;
    @XmlElement
    private String MESSAGE;

    /**
     * Tag Fields Example Max
     * Length Optional/
     * Mandatory Remarks
     * TYPE RCIRESP RCIRESP 10 M Response Type
     * TXNID <Transaction ID> CI070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Cash-In Transaction. Used
     * for informative purpose.
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction status
     * Failed, Success etc
     * <TRID> <transfer ID>
     * 4.2.
     * M
     */

}
