package af.asr.youtap.message.request;

public class MerchantTransactionRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private int merchantPin;
    /**
     * Customer contact phone number
     */
    private String contactMSISDN;

    private String customerSearchData;
    private String paymentType;
    private String workingCurrency;
    private double workingAmount;
    private String idData;
    private String fingerData;
    /**
     * Mobile Network operator name for Top ups
     * etc
     */
    private String mno;


    private MerchantTransactionRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.customerSearchData = builder.customerSearchData;
        this.fingerData = builder.fingerData;
        this.paymentType = builder.paymentType;
        this.workingCurrency = builder.workingCurrency;
        this.workingAmount  =builder.workingAmount;
        this.idData = builder.idData;
        this.merchantPin = builder.merchantPin;
        this.mno = builder.mno;
    }

    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private int merchantPin;
        /**
         * Customer contact phone number
         */
        private String contactMSISDN;

        private String customerSearchData;
        private String paymentType;
        private String workingCurrency;
        private double workingAmount;
        private String idData;
        private String fingerData;
        /**
         * Mobile Network operator name for Top ups
         * etc
         */
        private String mno;


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

        public Builder withCustomerData(String customerSearchData)
        {
            this.customerSearchData = customerSearchData;
            return this;
        }

        public Builder withFingerData(String fingerData)
        {
            this.fingerData = fingerData;
            return this;
        }

        public Builder withPaymentType(String paymentType)
        {
            this.paymentType = paymentType;
            return  this;
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

        public Builder withIdData(String idData)
        {
            this.idData = idData;
            return  this;
        }

        public Builder withMerchantPin(int merchantPin)
        {
            this.merchantPin = merchantPin;
            return this;
        }

        public Builder withMNO(String mno)
        {
            this.mno = mno;
            return  this;
        }

        public MerchantTransactionRequest build()
        {
            return new MerchantTransactionRequest(this);
        }

    }

    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,MerchantPin=%d,CustomerSearchData=%s,PaymentType=%s,WorkingCurrency=%s,WorkingAmount=%f", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.merchantPin, this.paymentType, this.workingCurrency, this.workingAmount );
    }
}
