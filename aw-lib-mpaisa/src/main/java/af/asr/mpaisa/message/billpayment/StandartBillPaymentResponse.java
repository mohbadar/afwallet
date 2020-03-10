package af.asr.mpaisa.message.billpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Standard Bill Payment (From Wallet)
 * 4.16.3.
 * Standard Bill Payment transactions generally means in which bill is present in the system .
 Response XML
     <?xml version="1.0"?>
     <COMMAND>
     <TYPE> SCPMBRESP </TYPE>
     <TXNID><Transaction ID></TXNID>
     <TRID><Transfer ID></TRID>
     <TXNSTATUS><Transaction Status></TXNSTATUS>
     <BILLCCODE>< Bill Company Code ></BILLCCODE>
     <BDUDATE><Bill payment due date></BDUDATE>
     <AMOUNT><Amount></AMOUNT>
     </COMMAND>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class StandartBillPaymentResponse {

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
     * BDUDATE <Bill payment due
     * date> M This will be system date.
     * AMOUNT <Amount> M Numeric Only. Should
     * accept
     * <TRID> <transfer ID>
     * 100
     * 10
     * M
     */
}
