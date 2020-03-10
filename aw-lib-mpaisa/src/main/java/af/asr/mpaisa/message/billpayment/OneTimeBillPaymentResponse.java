package af.asr.mpaisa.message.billpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Pay Bill Payment (From Wallet)  One  Time/Prepaid  Bill
 * Pre-paid transactions generally means in which either a bill is not present in the system or in cases
 * where there is no bills at all in the system like in case of Insurance Premium Payments or Cable TV
 * top up’s.
 *
 *
 Response XML
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE>CPMBRESP</TYPE>
 <TXNID><Transaction ID></TXNID>
 <TRID><Transfer ID></TRID>
 <TXNSTATUS><Transaction Status></TXNSTATUS>
 <BILLCCODE>< Bill Company Code ></BILLCCODE>
 <BDUDATE><Bill payment due date></BDUDATE>
 <AMOUNT><Amount></AMOUNT>
 <MESSAGE>Message</MESSAGE>
 </COMMAND>
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class OneTimeBillPaymentResponse {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int TRID;
    @XmlElement
    private int TXNSTATUS;
    @XmlElement
    private int BILLCCODE;
    @XmlElement
    private String BDUDATE;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private String MESSAGE;


    /**
     * Tag Fields Example Max
     * Leng
     * th Optional
     * /Manda
     * tory Remarks
     * TYPE CPMBRESP CPMBRESP 10 M Response Type
     * TXNID <Transaction ID> BP070608.1512.00
     * 0001 20 M M-Paisa Transaction ID
     * for the Bill Payment
     * Transaction
     * TXNSTATUS <Transaction Status> 200 5 M Various transaction
     * status Failed, Success etc
     * BILLCCODE < Bill Company Code > 9942223 15 M Numeric Only.
     * MESSAGE <Message> message M BDUDATE <Bill payment due
     * date> Message defined for the
     * service.
     * This will be system date.
     * AMOUNT <Amount> <TRID> <transfer ID>
     * M
     * 100
     * 10
     * M
     * M
     * Numeric Only. Should
     * accept
     */
}
