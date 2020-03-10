package af.asr.youtap.message.request;

public class MerchantLoginRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String merchantPin;

    public MerchantLoginRequest(Builder builder)
    {
        this.merchantId = builder.merchantId;
        this.terminalId=builder.terminalId;
        this.messageType=builder.messageType;
        this.transactionId=builder.transactionId;
        this.merchantPin = builder.merchantPin;
    }

    public static class Builder{

        public static Builder newInstance()
        {
            return new Builder();
        }

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String merchantPin;

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

        public Builder withMerchantPin(String merchantPin){
            this.merchantPin = merchantPin;
            return this;
        }

        public MerchantLoginRequest build()
        {
            return new MerchantLoginRequest(this);
        }

    }

    public String getMessage(){
        return
                String.format("MessageType=%s,TerminalId=%s,MerchantPin=%s,MerchantId=%s,TransactionId=%s", this.messageType,this.terminalId,this.merchantPin, this.merchantId, this.terminalId);
    }
}
