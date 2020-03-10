package af.asr.youtap.message.request;

public class MerchantChangePinRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private int merchantPin;
    private int newPin;


    private MerchantChangePinRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.merchantPin = builder.merchantPin;
        this.newPin = builder.newPin;
    }

    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private int merchantPin;
        private int newPin;


        public static Builder newInstance(){
            return  new Builder();
        }


        public Builder withMessageType(String messageType){
            this.messageType= messageType;
            return this;
        }

        public Builder withTerminalId(String terminalId){
            this.terminalId = terminalId;
            return this;
        }

        public Builder withMerchantId(String merchantId){
            this.merchantId = merchantId;
            return this;
        }

        public Builder withTransactionId(String transactionId)
        {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withMerchantPin(int merchantPin)
        {
            this.merchantPin = merchantPin;
            return this;
        }



        public Builder withNewPin(int newPin)
        {
            this.newPin = newPin;
            return this;
        }

        public MerchantChangePinRequest build()
        {
            return new MerchantChangePinRequest(this);
        }
    }

    public String getMessage()
    {
        return String.format("MessageType=%s,MerchantId=%s,TransactionId=%s,TerminalId=%s,MerchantPin=%d,NewPin=%d", this.messageType, this.merchantId, this.transactionId, this.terminalId, this.merchantPin, this.newPin);
    }
}
