package af.asr.mpaisa.message.domestic_money_transfer.choice2;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * No receiver side confirmation required. This option is also known as “Zero Confirmation of P2P”.
 * This is a single step transaction and the receiver user never has to confirm the transfer to beM
 * completed, i.e. as soon as the transaction is initiated successfully by the sender, the transfer is
 * completed.
 * Request XML
 * <?xml version="1.0"?>
 <COMMAND>
 <TYPE>CTMRRESP</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TXNSTATUS><Transaction Status></TXNSTATUS >
 <TRID><Transfer ID></TRID>
 <MESSAGE>Transfer Successful to <Payee MSISDN>: transaction amount: 1.00 Rs. , charges: 1.00
 Rs., commission: 0.0 Rs., transaction Id: PP110516.0257.C00003, net debit amount :2.00 Rs., new
 balance :92.00 Rs..</MESSAGE>
 <IVR-RESPONSE>9013#7766554411|6|2.12|2|PP121031.1634.C00002|12.2012|944.9944#</
 IVR- RESPONSE>
 </COMMAND>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class P2PConfigChoice2Response {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private int TRID;
    @XmlElement
    private String MESSAGE;
    @XmlElement
    private String IVRRESPONSE;


    /**
     * Tag Fields Example Max
     * Lengt
     * h Optional/
     * Mandatory Remarks
     * TYPE CTMRRESP CTMRRESP 10 M Response Type
     * TXNID <Transaction ID> MT070608.1512.000001 20 M M-Paisa Transaction ID for
     * the Transfer M-money
     * Transaction.
     * Used for informative purpose.
     * TXNSTATUS <Transaction Status>
     * <TRID> <transfer ID>
     * IVR-
     * IV <I R V -
     * RESPONSEIVR R R R E e S s P po O n N se S > E
     * -RESPONSE
     * 200
     * 5
     * M
     * Various transaction status
     * Failed, Success etc
     * M
     * 9200103##27071626-
     * 51504-3411-1|
     * 61|12:.5172|520|
     * PP121031
     * 12|944.9944#
     * MM
     * IVIVRR–
     * ReEsSpPoOnNseSECo de
     */
}
