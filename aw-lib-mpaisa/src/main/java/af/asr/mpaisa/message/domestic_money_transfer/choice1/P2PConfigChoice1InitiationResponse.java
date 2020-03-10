package af.asr.mpaisa.message.domestic_money_transfer.choice1;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Subscribers can transfer points to each other using the domestic money transfer service.
 * P2P Services Options
 * In M-Paisa system there will be following Options available for P2P Money transfer service.
 *
 * This is a normal P2P transaction. It is also known as “Always Confirmation Required” mode of
 * Domestic Money transfer. The receiver user must always confirm this transaction in order for
 * the transfer to complete.
 *
 *
 Response XML (Initiation)
 <?xml version="1.0"?>
 <COMMAND>M
 <TYPE> TMRESP</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS >
 <MESSAGE><P2P initiate Message></MESSAGE>
 <TRID><Transfer ID></TRID>
 <IVR-RESPONSE>9013#7766554411|6|2.12|2|PP121031.1634.C00002|12.2012|944.9944#
 </IVR-RESPONSE>
 </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class P2PConfigChoice1InitiationResponse {

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
     * TYPE CTMRESP CTMRESP 10 M Response Type
     * TXNID <Transaction ID> MT070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Transfer M-money
     * Transaction, Used for
     * informative purpose.
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction status
     * Failed, Success etc
     * MESSAGE <P2P initiate Message> Money Transfer is
     * initiated. Payee will have
     * to confirm the withdrawl
     * by entering sender's
     * number. Thank you for
     * using M-Paisa service. M Message corresponding to
     * <Transaction Status>
     * <TRID> <transfer ID> IVR-
     * RESPONSE IVR-RESPONSE
     * M
     * M
     * 9013#7766554411|
     * 6|2.12|2|PP121031
     * .1634.C00002|12.20
     * 12|944.9944#
     * IVR-RESPONSE Code
     */
}
