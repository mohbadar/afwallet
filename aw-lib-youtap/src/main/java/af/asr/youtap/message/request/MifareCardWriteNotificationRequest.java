package af.asr.youtap.message.request;

import lombok.Data;

@Data
/**
 * Indicates the resulting prepaid card update status.
 */
public class MifareCardWriteNotificationRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String cardType;
    private String context;
    private String customerData;
    /**
     * Status of the NFC Card write operation
     */
    private String commandStatus;

    /**
     * Transaction sequence reference number
     */
    private String writeReference;


    /**
     * MessageType=MifCardWriteNotification,TransactionId=0000000030,TerminalId=98378265,M
     * erchantId=8888888888888,CardType=MIFARECLASSIC,Context=MEePurse,CustomerData=(NFCTa
     * gId=521BFD5B),WriteReference=5976024341534212105,CommandStatus=NOTOK
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=MifCardWriteNotification,TransactionId=0000000030,TerminalId=98378265,M\n" +
                "erchantId=8888888888888,CardType=MIFARECLASSIC,Context=MEePurse,CustomerData=(NFCTa\n" +
                "gId=521BFD5B),WriteReference=5976024341534212105,CommandStatus=NOTOK");
    }
}
