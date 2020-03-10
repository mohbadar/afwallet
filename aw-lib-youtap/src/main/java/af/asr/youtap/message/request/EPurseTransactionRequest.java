package af.asr.youtap.message.request;

import lombok.Data;

@Data
public class EPurseTransactionRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String paymentType;
    private String workingCurrency;
    private double workingAmount;
    private String cardType;
    private String cardData;
    private String context;

    /**
     * MessageType=EPurseTransaction,TransactionId=0000000303,TerminalId=98944138,Merchant
     * Id=33333333333,CardType=MIFARECLASSIC,PaymentType=TOPUP,CustomerData=(NFCTagId=521B
     * FD5B),CardData=(S2=(ID=0015,B=0,D=0401202401008029070000000000000026898400001087998
     * 60000000000000007000000000000305DE2542C1F48DC4E),S5=(ID=88BB,B=0,D=2101202401A61C02
     * 000000660000000000000000000000000000000000000000000000000000000E2B224F741680332A),S
     * 6=(ID=88BB,B=0,D=00000000000000000000000000000000E0FFFF7F1F000080E0FFFF7F19E619E6E0
     * FFFF7F1F000080E0FFFF7F19E619E6),S7=(ID=88BB,B=0,D=A5C2EE96DCB74BCE067AE692CB76F57C8
     * 258D00240480232442203208218BD024048023244F60120E69B4B925FD22F3A),S8=(ID=88BB,B=0,D=
     * 329C0E08715E3605DD12381CE0925C148218D0024048023244A20F008218BD024048023244F6012036B
     * AFD5BB13C81D2)),WorkingCurrency=NZD,WorkingAmount=1.10,Context=MEePurse
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CardType=%s,PaymentType=%s,CustomerData=%s,CardData=%s,WorkingCurrency=%s,WorkingAmount=%f,Context=%s", this.messageType,this.transactionId, this.terminalId, this.merchantId, this.cardType, this.paymentType, this.customerData, this.cardData, this.workingCurrency, this.workingAmount,this.context);
    }
}
