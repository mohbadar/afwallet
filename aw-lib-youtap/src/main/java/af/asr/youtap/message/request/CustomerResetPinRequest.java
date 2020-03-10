package af.asr.youtap.message.request;

public class CustomerResetPinRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String fingerData;


    public CustomerResetPinRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.customerData = builder.customerData;
        this.fingerData = builder.fingerData;
    }

    public static class Builder {


        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String customerData;
        private String fingerData;


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

        public Builder withFingerData(String fingerData)
        {
            this.fingerData = fingerData;
            return this;
        }

        public CustomerResetPinRequest build()
        {
            return new CustomerResetPinRequest(this);
        }

    }

    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CustomerData=%s,FingerData%s", this.messageType, this.transactionId, this.terminalId, this.merchantId,this.customerData, this.fingerData);
    }
}
