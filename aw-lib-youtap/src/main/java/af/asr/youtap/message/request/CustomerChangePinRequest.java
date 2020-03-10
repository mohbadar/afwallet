package af.asr.youtap.message.request;

public class CustomerChangePinRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private int merchantPin;


    private CustomerChangePinRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.customerData = builder.customerData;
        this.merchantPin = builder.merchantPin;
    }

    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String customerData;
        private int merchantPin;


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

        public Builder withCustomerData(String customerData)
        {
            this.customerData = customerData;
            return this;
        }

        public Builder withMerchantPin(int merchantPin)
        {
            this.merchantPin = merchantPin;
            return this;
        }

        public CustomerChangePinRequest build()
        {
            return new CustomerChangePinRequest(this);
        }


    }

    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantPin=%s,MerchantId=%s,CustomerData=%s", this.messageType, this.transactionId, this.terminalId, this.merchantPin, this.merchantId, this.customerData);
    }
}
