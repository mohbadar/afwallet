package af.asr.youtap.message.request;

import lombok.Data;

/**
 * Requesting approval from the server to perform a purse transaction with an EPurse card.
 */
@Data
public class EPurseTransactionAuthorizeRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String cardType;
    private String context;
    private String cardData;
    private String merchantPin;
    private String paymentType;
    private String workingCurrency;
    private double workingAmount;
    private String date;
    private String time;
    /**
     * EPurse Details
     */
    private String ePurseData;
    /**
     * The EPurseLog raw data to be written on the card
     */
    private String ePurseLog;
    private String app;

    /**
     * MessageType=EPurseTransactionAuthorize,TransactionId=0000000030,TerminalId=98378265
     * ,MerchantId=8888888888888,CardType=MIFARECLASSIC,Context=MEePurse,CustomerData=(NFC
     * TagId=39BD361E),EPurseLog=965BBB40060084000000009487656701DAD313DC01CDBB9AB34955C16
     * F8E98CA,PaymentType=TOPUP,WorkingAmount=100,Date=03/05/2016,Time=12:29:11,MERCHANTP
     * IN=****,App=v1.8b73a,WorkingCurrency=IDR,EPurseData=(EPurseId=39BD361E,EPurseCurren
     * cy=IDR,EPurseBalance=95807,EPurseCounter=7ffffff0)
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CardType=%s,Context=%s,CustomerData=%s,EPurseLog=%s,PaymentType=%s,WorkingAmount=%f,Date=%s,Time=%s,MERCHANTPIN=%s,App=%s,WorkingCurrency=%s,EPurseData=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.cardType, this.context, this.customerData, this.ePurseLog, this.paymentType, this.workingAmount, this.date,this.time, this.merchantPin, this.app, this.workingCurrency, this.ePurseData);
    }
}
