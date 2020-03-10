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
 Request XML (Confirmation)
 <?xml version="1.0"?>
 <COMMAND>
 <TYPE>CTMRREQ</TYPE>
 <MSISDN><Recipient MSISDN></MSISDN>
 <TXNID><Transaction Id></TXNID>
 <STATUS><Status></STATUS>
 <LANGUAGE1><Payer Language></LANGUAGE1>
 </COMMAND>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class P2PConfigChoice1ConfirmationRequest {

    @XmlElement
    private String TYPE;
    @XmlElement
    private String MSISDN;
    @XmlElement
    private String TXNID;
    @XmlElement
    private int STATUS;
    @XmlElement
    private int LANGUAGE1;

    /**
     * Tag Fields Example Max
     * Length O
     * -
     * Optional/
     * M
     * –
     * Mandatory Remarks
     * TYPE CTMRREQ CTMRREQ 10 M Request Type
     * MSISDN Recipient MSISDN 9942222 15 M Should be without
     * country code.
     * TXNID <Transaction ID> MT070608.1512.000 20 M M-PaisaM
     * 001
     * LANGUAGE1 <Language>
     * STATUS <Status>
     * 0
     * Transaction ID for
     * the Transfer M-
     * money Transaction,
     * Used for informative
     * purpose.
     * 1
     * O (Tag is
     * mandatory) Numeric only Payer
     * Language Code. This
     * code must be
     * defined in M-Paisa
     * system.
     * M This field would be
     * used when end
     * subscriber has either
     * cancelled or has not
     * responded (session
     * expire) to the
     * request
     * 0- approve & 1-
     * cancel
     */
}
