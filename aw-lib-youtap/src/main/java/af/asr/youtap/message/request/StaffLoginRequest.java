package af.asr.youtap.message.request;

public class StaffLoginRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String staffPin;

    public StaffLoginRequest(Builder builder)
    {
        this.merchantId = builder.merchantId;
        this.terminalId=builder.terminalId;
        this.messageType=builder.messageType;
        this.transactionId=builder.transactionId;
        this.staffPin = builder.staffPin;
    }

    public static class Builder{

        public static Builder newInstance()
        {
            return new StaffLoginRequest.Builder();
        }

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String staffPin;

        public Builder withMessageType(String messageType)
        {
            this.messageType=messageType;
            return this;
        }

        public Builder withTerminalId(String terminalId)
        {
            this.terminalId=terminalId;
            return this;
        }

        public Builder withMerchantId(String merchantId){
            this.merchantId=merchantId;
            return this;
        }

        public Builder withTransactionId(String transactionId)
        {
            this.transactionId=transactionId;
            return this;
        }

        public Builder withStaffPin(String staffPin){
            this.staffPin = staffPin;
            return this;
        }

        public StaffLoginRequest build()
        {
            return new StaffLoginRequest(this);
        }

    }

    public String getMessage(){
        return
                String.format("MessageType=%s,TerminalId=%s,StaffPin=%s,MerchantId=%s,TransactionId=%s", this.messageType,this.terminalId,this.staffPin, this.merchantId, this.terminalId);
    }
}
