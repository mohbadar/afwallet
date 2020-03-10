package af.asr.mpaisa.message.onlinecustomerbillpayment;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * <COMMAND>
 * <TYPE>ONLNBP</TYPE>
 * <MSISDN1><payer MSISDN></MSISDN1>
 * <MSISDN2><OTHER PARTY MSISDN></MSISDN2>
 * <PIN><PIN></PIN>
 * <PROVIDER><PROVIDER></PROVIDER>
 * <BPROVIDER><BILLER PROVIDER></BPROVIDER>
 * <PAYMENT_INSTRUMENT>WALLET</PAYMENT_INSTRUMENT>
 * <PAYID><PAYMENT ID></PAYID>
 * <BILLERCODE><BILLERCODE></BILLERCODE>
 * <BILLANO><BILLER ACCOUNT NO></BILLANO>
 * <AMOUNT><AMOUNT></AMOUNT>
 * <BNAME><Biller Name></BNAME>
 * <REQUESTORID>INTERNAL</REQUESTORID>
 * <CELLID>1</CELLID>
 * <FTXNID>1</FTXNID>
 * <LANGUAGE1>1</LANGUAGE1>
 * <LANGUAGE2>1</LANGUAGE2>
 * </COMMAND>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class OnlineCustomerBillPaymentRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN1;
    @XmlElement
    private String MSISDN2;
    @XmlElement
    private int PIN;
    @XmlElement
    private int PROVIDER;
    @XmlElement
    private int BPROVIDER;
    @XmlElement
    private String PAYMENT_INSTRUMENT;
    @XmlElement
    private int PAYID;
    @XmlElement
    private String BILLERCODE;
    @XmlElement
    private String BILLANO;
    @XmlElement
    private BigDecimal AMOUNT;
    @XmlElement
    private String BNAME;
    @XmlElement
    private  String REQUESTORID;
    @XmlElement
    private String CELLID;
    @XmlElement
    private String FTXNID;
    @XmlElement
    private int LANGUAGE1;
    @XmlElement
    private  int LANGUAGE2;


    /**
     * TAG
     * Fields
     * Remarks
     * Max
     * Length Field Type
     * Optional/
     * Mandatory
     * TYPE ONLNBP ONLNBP 10 M Request Type
     * MSISDN1 <Payer MSISDN> 9942222 15 M Payer MSISDN should
     * be without country
     * code.
     * MSISDN2 OTHER PARTY MSISDN 998888888 15 O It will use in case of
     * bill payment for
     * other
     * PIN <Payer PIN> 3946 4 M Numeric Only.
     * default “0000” will
     * be considered if not
     * specified
     * BILLERCODE < Bill Company Code > 9942223 15 M Alpha Numeric
     * BILLANO < Account number With
     * Company > 9942222 15 o It will use in case of
     * utility bill payment
     * BNAME <Biller Name> asdf M Alpha Numeric
     * AMOUNT <Amount> 100 M Numeric Only.
     * Should accept
     * REQUESTORID < REQUESTOR ID > INTERNAL O Its value must Be
     * 10
     * INTERNAL , Any
     * external value will be
     * overridden by internal
     * value.
     * LANGUAGE1
     * <Payer Language>
     * 0
     * 1
     * O (Tag is
     * mandatory)
     * Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system.
     * LANGUAGE1
     * <Payee Language>
     * 1
     * M
     * Numeric only Payer
     * Language Code
     * This code must be
     * defined in M-Paisa
     * system.
     * PAYMENT_INSTR
     * UMENT <
     * WALLET M PROVIDER < PROVIDER> 101 M Provider id
     * PAYID < PAY ID> 12 M Wallet Type ID
     * BPROVIDER < BPROVIDER> 100 M Biller Provider ID
     * CELLID <CELL ID> 1 44 M Alphanumeric
     * FTXNID <FTXN ID> 1 44 M Alphanumeric
     */
}
