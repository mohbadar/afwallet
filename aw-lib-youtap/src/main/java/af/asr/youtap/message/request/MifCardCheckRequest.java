package af.asr.youtap.message.request;

import lombok.Data;

@Data
public class MifCardCheckRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String cardType;
    private String context;
    private String cardData;



    /**
     * MessageType=MifCardCheck,TransactionId=0000000053,TerminalId=98944138,MerchantId=33
     * 33333333333,CardType=MIFARECLASSIC,Context=MEePurse,CustomerData=(NFCTagId=521BFD5B
     * ),CardData=(S0=(ID=0000,B=0,D=521BFD5BEF880400C18514955530481160000400150081077320B
     * B88BB88BB88BB880000000000000000000000000000000000000000696789C1000000000000))
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CardType=%s,Context=%s,CustomerData=%s,CardData=%s", this.messageType, this.transactionId, this.terminalId,  this.merchantId, this.cardType, this.context, this.customerData, this.cardData);
    }

}
