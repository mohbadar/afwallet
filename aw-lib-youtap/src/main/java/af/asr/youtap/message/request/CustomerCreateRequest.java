package af.asr.youtap.message.request;

public class CustomerCreateRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;


    private CustomerCreateRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.customerData = builder.customerData;
    }

    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String customerData;


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

        public CustomerCreateRequest build()
        {
            return new CustomerCreateRequest(this);
        }
    }

    /**
     * Request Example:
     * • Command Message Subscriber:
     * MessageType=CustomerCreate,TransactionId=0000000015,TerminalId=98944138,Merc
     * hantId=021333333,CustomerData=(GivenName=JOE,SurName=BLOGGS,DOB=19801225,Con
     * tactPhone=64211883899,MobMonPin=1236,CustomerType=SUBSCRIBER)
     * • Command Message Merchant:
     * MessageType=CustomerCreate,TransactionId=0000000062,TerminalId=98944138,Merc
     * hantId=021333333,CustomerData=(GivenName=CM,SurName=DME,DOB=19750303,Contact
     * Phone=64211773281,MobMonPin=1234,CustomerType=MERCHANT,AssignedTid=98911111)
     * @return create customer message
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CustomerData=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.customerData);
    }
}
