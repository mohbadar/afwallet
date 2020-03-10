package af.asr.youtap.message.request;

import java.time.LocalDate;

public class ExchangeTransactionRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String staffPin;
    private String customerId;
    private String paymentType;
    private String sourceCurrency;
    private String destinationCurrency;
    private String workingCurrency;
    private double sendingAmountExclFees;
    private String fxRate;
    private String fee;
    private String costToSend;
    private double receiveAmount;
    private String billPayeeId;
    private String billPayeeReference;
    private String contactMSISDN;
    private double workingAmount;


    public ExchangeTransactionRequest(Builder builder)
    {
        this.messageType = builder.messageType;
        this.terminalId = builder.terminalId;
        this.merchantId = builder.merchantId;
        this.transactionId = builder.transactionId;
        this.customerId = builder.customerId;
        this.paymentType = builder.paymentType;
        this.sourceCurrency = builder.sourceCurrency;
        this.destinationCurrency  = builder.destinationCurrency;
        this.workingCurrency = builder.workingCurrency;
        this.staffPin = builder.staffPin;
        this.sendingAmountExclFees = builder.sendingAmountExclFees;
        this.fxRate = builder.fxRate;
        this.fee = builder.fee;
        this.costToSend = builder.costToSend;
        this.receiveAmount = builder.receiveAmount;
        this.billPayeeId = builder.billPayeeId;
        this.billPayeeReference = builder.billPayeeReference;
        this.contactMSISDN = builder.contactMSISDN;
        this.workingAmount = builder.workingAmount;
    }


    public static class Builder {

        private String messageType;
        private String terminalId;
        private String merchantId;
        private String transactionId;
        private String staffPin;
        private String customerId;
        private String paymentType;
        private String sourceCurrency;
        private String destinationCurrency;
        private String workingCurrency;
        private double sendingAmountExclFees;
        private String fxRate;
        private String fee;
        private String costToSend;
        private double receiveAmount;
        private String billPayeeId;
        private String billPayeeReference;
        private String contactMSISDN;
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

        public Builder withStaffPin(String staffPin)
        {
            this.staffPin = staffPin;
            return this;
        }

        public Builder withSendingAmountExclFees(double sendingAmountExclFees)
        {
            this.sendingAmountExclFees = sendingAmountExclFees;
            return this;
        }

        public Builder withFxRate(String fxRate)
        {
            this.fxRate = fxRate;
            return  this;
        }

        public Builder withFee(String fee)
        {
            this.fee  = fee;
            return this;
        }

        public Builder withCostToSend(String costToSend)
        {
            this.costToSend = costToSend;
            return this;
        }

        public Builder withReceiveAmount(double receiveAmount)
        {
            this.receiveAmount = receiveAmount;
            return this;
        }

        public Builder withWorkingAmount(double workingAmount)
        {
            this.workingAmount = workingAmount;
            return this;
        }


        public Builder withBillPayeeId(String billPayeeId)
        {
            this.billPayeeId = billPayeeId;
            return this;
        }

        public Builder withBillPayeeReference(String billPayeeReference)
        {
            this.billPayeeReference = billPayeeReference;
            return this;
        }

        public Builder withContactMSISDN(String contactMSISDN)
        {
            this.contactMSISDN = contactMSISDN;
            return this;
        }

        public ExchangeTransactionRequest build()
        {
            return new ExchangeTransactionRequest(this);
        }
    }

    /**
     * Request Example:
     * •
     * Command Message (Send Money):
     * MessageType=ExchangeTransaction,Date=06/03/2013,Time=12:38:04,TransactionId=
     * 0000000017,TerminalId=98378273,MerchantId=86637,StaffPin=123,CustomerId=8671
     * 7,PaymentType=DMM,SourceCurrency=NZD,DestinationCurrency=DFJD,WorkingCurrenc
     * y=NZD,WorkingAmount=1.00,SendingAmountExclFees=1.0,FxRate=1.3080636,Fee=5.0,
     * CostToSend=6.0,ReceivedAmount=1.31,ContactMsisdn=6797012106
     *
     *
     *
     * Command Message (Pay Bill):
     * MessageType=ExchangeTransaction,TransactionId=0000000019,TerminalId=98378273
     * ,MerchantId=86637,StaffPin=123,CustomerId=86717,PaymentType=BILL,SourceCurre
     * ncy=NZD,DestinationCurrency=DFJD,WorkingCurrency=NZD,WorkingAmount=1.00,Send
     * ingAmountExclFees=1.0,FxRate=1.3080636,Fee=5.0,CostToSend=6.0,ReceivedAmount
     * =1.31,ContactMsisdn=6797095009,BillPayeeID=99,BillPayeeReference=12345678901
     * 2
     * @return
     */
    public String getMessage()
    {
        LocalDate now = LocalDate.now();
        String date = String.format("%d/%d/%d", now.getDayOfMonth(), now.getMonthValue(), now.getYear());
        return  String.format("MessageType=%s,Date=%s,Time=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,StaffPin=%s,CustomerId=%s,PaymentType=%s,SourceCurrency=%s,DestinationCurrency=%s,WorkingCurrency=%s,WorkingAmount=%s,SendingAmountExclFees=%f,FxRate=%s,Fee=%s,CostToSend=%s,ReceivedAmount%f,ContactMsisdn=%s", this.messageType, date, "", this.transactionId, this.terminalId, this.merchantId, this.staffPin, this.customerId, this.paymentType, this.sourceCurrency, this.destinationCurrency, this.workingCurrency, this.workingAmount, this.sendingAmountExclFees, this.fxRate, this.fee, this.costToSend, this.receiveAmount, this.contactMSISDN );
    }

}