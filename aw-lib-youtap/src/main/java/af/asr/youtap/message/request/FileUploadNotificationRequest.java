package af.asr.youtap.message.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
/**
 * Indicates a file was uploaded to the file server
 */
public class FileUploadNotificationRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String fileDescriptor;


    /**
     * MessageType=FileUploadNotification,TransactionId=0000000030,TerminalId=98378265,Mer
     * chantId=8888888888888,FileDescriptor=(Name=offline_20160503194229_94876567.txt,Info
     * Type=PURSELOGS)
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,FileDescriptor=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.fileDescriptor);
    }

}
