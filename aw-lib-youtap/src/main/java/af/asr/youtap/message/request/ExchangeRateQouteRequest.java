package af.asr.youtap.message.request;

public class ExchangeRateQouteRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerId;
    private String paymentType;
    private String sourceCurrency;
    private String destinationCurrency;
    private String workingCurrency;
    private String customerData;
    private double workingAmount;

    private ExchangeRateQouteRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.customerData = builder.customerData;
        this.customerId = builder.customerId;
        this.paymentType = builder.paymentType;
        this.sourceCurrency = builder.sourceCurrency;
        this.destinationCurrency  = builder.destinationCurrency;
        this.workingCurrency = builder.workingCurrency;
        this.workingAmount = builder.workingAmount;

    }

    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String customerId;
        private String paymentType;
        private String sourceCurrency;
        private String destinationCurrency;
        private String workingCurrency;
        private String customerData;
        private double workingAmount;


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

        public Builder withCustomerId(String customerId)
        {
            this.customerId = customerId;
            return this;
        }

        public Builder withPaymentType(String paymentType)
        {
            this.paymentType = paymentType;
            return this;
        }

        public Builder withSourceCurrency(String sourceCurrency)
        {
            this.sourceCurrency = sourceCurrency;
            return this;
        }

        public Builder withDestinationCurrency(String destinationCurrency)
        {
            this.destinationCurrency = destinationCurrency;
            return this;
        }

        public Builder withWorkingCurrency(String workingCurrency)
        {
            this.workingCurrency = workingCurrency;
            return this;
        }

        public Builder withWorkingAmount(double workingAmount)
        {
            this.workingAmount = workingAmount;
            return  this;
        }

        public ExchangeRateQouteRequest build()
        {
            return new ExchangeRateQouteRequest(this);
        }
    }

    /**
     * Example Request:
     *
     * MessageType=ExchangeQuotation,TransactionId=0000000964,TerminalId=21908856,Merchant
     * Id=86637,CustomerId=86637,PaymentType=DMM,SourceCurrency=NZD,DestinationCurrency=DT
     * OP,WorkingCurrency=NZD,WorkingAmount=1.00
     *
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CustomerId=%s,PaymentType=%s,SourceCurrency=%s,DestinationCurrency=%s,WorkingCurrency=%s,WorkingAmount=%f, CustomerData=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.customerId, this.paymentType, this.sourceCurrency, this.destinationCurrency, this.workingCurrency, this.workingAmount, this.customerData);
    }
}
