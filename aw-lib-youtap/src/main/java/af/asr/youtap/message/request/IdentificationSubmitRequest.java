package af.asr.youtap.message.request;


/**
 * Adds subscriber’s identification details after registration.
 */
public class IdentificationSubmitRequest {


    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String customerId;
    private String idData;
    private String fingerData;


    private IdentificationSubmitRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.customerData = builder.customerData;
        this.fingerData = builder.fingerData;
        this.customerId = builder.customerId;
        this.idData = builder.idData;
    }

    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String customerData;
        private String customerId;
        private String idData;
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

        public Builder withCustomerSearchData(String customerData)
        {
            this.customerData = customerData;
            return this;
        }

        public Builder withFingerData(String fingerData)
        {
            this.fingerData = fingerData;
            return this;
        }

        public Builder withPaymentType(String customerId)
        {
            this.customerId = customerId;
            return  this;
        }


        public Builder withIdData(String idData)
        {
            this.idData = idData;
            return  this;
        }


        public IdentificationSubmitRequest build()
        {
            return new IdentificationSubmitRequest(this);
        }

    }

    /**
     * Request Example:
     * MessageType=IdentificationSubmit,TransactionId=0000000016,TerminalId=98944138,Merch
     * antId=021333333,FingerData=464D52002032300000000126000000C0010E00C800C801000208642C
     * 405E001E9D0040290026AD004035002E2100406600360F008074003AA3008089003AE4004062003EA80
     * 08080004E9900407A00519F0080CA00514B0040A6005A6000409C005C7000401C005E3400405500622B
     * 0080990068D80080860069A8004084006DAD0080900072CB0080340074B60080890076B600800A007D4
     * 400808E007DBA008030008A3600400C00A84B00803500C84C0040C800C83B00807000D2410040D400C5
     * 3600806100E04F00807600E24100401500E95700407E00E93B0040B000EA3500407900EC4A00407500E
     * D5500406100F65B00404A0100600080A2010C96004071011C740040C50122250040960132910080BC01
     * 3299004096014617004091015885000000,CustomerId=64211883899,IdData=(idType=1,idName=J
     * OE BLOGGS,idNumber=AA1245780923,idCountry=NZ,idExpiry=20150311)
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,FingerData=%s,CustomerId=%s,IdData=%s, CustomerData=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.fingerData, this.customerId, this.idData, this.customerData);
    }
}
