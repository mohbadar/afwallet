package af.asr.youtap.message.request;

public class RegisterTagRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    /**
     * Register this tag as the first tag, or
     * subsequent tags, or replace the
     * primary ta
     */
    private String tagType;

    /**
     * To identify tag owner in SMS
     * messages
     */
    private String tagOwnerName;
    /**
     * Indicates which tag to replace
     */
    private String replacingTagType;
    /**
     * CustomerSearchData
     */
    private String customerSearchData;


    public RegisterTagRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.tagType = builder.tagType;
        this.tagOwnerName = builder.tagOwnerName;
        this.replacingTagType = builder.replacingTagType;
        this.customerSearchData = builder.customerSearchData;
    }

    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String tagType;
        private String tagOwnerName;
        private String replacingTagType;
        private String customerSearchData;

        public static Builder newInstance(){
            return new Builder();
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


        public Builder withTagType(String tagType)
        {
            this.tagType = tagType;
            return this;
        }

        public Builder withTagOwerName(String tagOwnerName){
            this.tagOwnerName = tagOwnerName;
            return this;
        }

        public Builder withReplacingTagType(String replacingTagType){
            this.replacingTagType = replacingTagType;
            return this;
        }

        public Builder withCustomerSearchData(String customerSearchData){
            this.customerSearchData = customerSearchData;
            return this;
        }

        public RegisterTagRequest build(){
            return new RegisterTagRequest(this);
        }

    }



}
