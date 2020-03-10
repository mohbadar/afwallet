package af.asr.youtap.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSearchRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String transactionSearchData;
    private String date;
    private String time;
    private String app;


    /**
     * MessageType=TransactionSearch,Date=01/07/2013,Time=13:16:40,TransactionId=000000009
     * 1,TerminalId=98944138,MerchantId=021333333,TransactionSearchData=(TransactionDate=2
     * 0130701131621,TransactionId=0000000090,TerminalId=98944138),App=v1.5b4
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,Date=%s,Time=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,TransactionSearchData=%s,App=%s", this.messageType, this.date, this.time, this.transactionId, this.terminalId, this.merchantId, this.transactionSearchData);
    }
}
