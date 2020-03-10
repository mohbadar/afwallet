package af.asr.youtap.message.request;

public class BalanceGetRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String merchantPin;
    private String customerData;
    private String balanceType;


    public BalanceGetRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.merchantPin = builder.merchantPin;
        this.customerData = builder.customerData;
        this.balanceType = builder.balanceType;
    }


    public static class Builder {


        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String merchantPin;
        private String customerData;
        private String balanceType;

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

        public Builder withMerchantPin(String merchantPin)
        {
            this.merchantPin = merchantPin;
            return this;
        }

        public Builder withCustomerData(String customerData)
        {
            this.customerData = customerData;
            return this;
        }

        public Builder balanceType(String balanceType)
        {
            this.balanceType = balanceType;
            return this;
        }

        public BalanceGetRequest build(){
            return new BalanceGetRequest(this);
        }
    }

    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,MerchantPin=%s,BalanceType=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.merchantPin, this.balanceType);
    }
}
