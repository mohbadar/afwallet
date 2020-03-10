package af.asr.youtap.message.request;

import lombok.Data;

@Data
public class EPurseCheckRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String cardType;
    private String cardData;

    /**
     * Example Message:
     *
     * MessageType=EPurseCheck,TransactionId=0000000300,TerminalId=98944138,MerchantId=333
     * 33333333,CardType=MIFARECLASSIC,CustomerData=(NFCTagId=521BFD5B),CardData=(S2=(ID=0
     * 015,B=0,D=0401202401008029070000000000000026898400001087998600000000000000070000000
     * 00000305DE2542C1F48DC4E),S5=(ID=88BB,B=0,D=2101202401A61C02000000660000000000000000
     * 000000000000000000000000000000000000000E2B224F741680332A),S6=(ID=88BB,B=0,D=0000000
     * 0000000000000000000000000E0FFFF7F1F000080E0FFFF7F19E619E6E0FFFF7F1F000080E0FFFF7F19
     * E619E6),S7=(ID=88BB,B=0,D=A5C2EE96DCB74BCE067AE692CB76F57C8258D00240480232442203208
     * 218BD024048023244F60120E69B4B925FD22F3A),S8=(ID=88BB,B=0,D=329C0E08715E3605DD12381C
     * E0925C148218D0024048023244A20F008218BD024048023244F6012036BAFD5BB13C81D2))
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CardType=%s,CustomerData=%s,CardData=%s", this.messageType, this.transactionId, this.terminalId,  this.merchantId, this.cardType, this.customerData, this.cardData);
    }
}
