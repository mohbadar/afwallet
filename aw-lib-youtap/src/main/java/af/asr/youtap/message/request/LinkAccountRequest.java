package af.asr.youtap.message.request;

import lombok.Data;

@Data
/**
 * A Subscriber might want to link their bank account with their mobile money account and transfer money in both
 * directions as well as get their bank balance
 */
public class LinkAccountRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;

    /**
     * MessageType=LinkAccount,TransactionId=0000000086,TerminalId=98944138,MerchantId=021
     * 333333,CustomerData=(BankAccountNo=1234567890000,LinkType=L,MobMonPin=1234,BankPin=
     * 5678,NFCTagId=0493F222BD26803C)
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CustomerData=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.customerData);
    }
}
