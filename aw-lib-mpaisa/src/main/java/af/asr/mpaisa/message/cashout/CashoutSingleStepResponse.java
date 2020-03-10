package af.asr.mpaisa.message.cashout;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <?xml version="1.0"?>
 * <COMMAND>
 * <TYPE>CCORESP</TYPE>
 * <TXNID><Transaction ID></TXNID>
 * <TXNSTATUS><Transaction Status></TXNSTATUS>
 * <MESSAGE><Txn Message></MESSAGE>
 * <IVR-RESPONSE> 9012#7777999111|5.0000|1.0000|0.0000|CO121101.1657.C00004|
 * 8.0000|31109.6040#
 * </IVR-RESPONSE>
 * </COMMAND>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class CashoutSingleStepResponse {

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

    /**
     * Tag Fields Example Max
     * Lengt
     * h Optional/
     * Mandatory Remarks
     * TYPE CCORESP CCORESP 10 M Response Type
     * TXNID <Transaction ID> CO070608.1512.000001 20 M M-Paisa Transaction ID for the
     * Cash-Out Transaction. Used for
     * informative purpose.
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction status
     * Failed, Success etc
     * MESSAGE <Txn Message> Cash Out success by
     * 7700000012. The details
     * are as follows: transaction
     * amount: 1.00 INR,
     * charges: 0.01 INR,
     * commission: 0.0 INR,
     * transaction Id:
     * CO110525.1616.C00002,
     * net debit amount 1.01 INR,
     * new balance: 242.99 INR. IVR-
     * RESPONSE IVR-RESPONSE 9012#7777999111|5.0000|1
     * .0000|0.0000|CO121101.16
     * 57.C00004|8.0000|31109.6
     * 040#
     * 4.3.
     * Message corresponding to
     * <Transaction Status>
     * M
     * IVR-RESPONSE CODE
     */
}
