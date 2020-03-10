package af.asr.youtap.message.request;

import lombok.Data;

@Data
public class AtomicCustomerCreateRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String idData;
    private String subscriberAddress;

    /**
     * MessageType=AtomicCustomerCreate,TransactionId=0000000070,TerminalId=98944138,Merch
     * antId=93703333333,CustomerData=(GivenName=ADA,SurName=LOVELACE,DOB=18151225,Contact
     * Phone=93700000007,MobMonPin=1234,CustomerType=AGENT,AssignedTid=12457808,FingerData
     * 1=464D5200203230000000011A000000C0010E00C800C801000208612A4096000DF10040AC00186E004
     * 0220025AC00402D002922004062002D0F008071002E9E0080860030EA0040C800425600807D004C9B00
     * 808A0050D30040840052A600805100562B0040B000585E004015005A33004091005AD9004085005DB00
     * 0808D0066B70080300069BD008091006ABE00802E00823F0080CD00AE3B00803500BC4E00807400BC43
     * 0040DA00BC3400806200CD4D00407800D0470040B600D13200807D00D44D00808200D53C00801900E05
     * B00803800E25C00806900E25E00803600E5E800404D00F0660080A500F4990040780109780040CA010C
     * 250080BD011C9E00409D011D9400409D01321A0040B801411E004099014488000000),IdData=(idTyp
     * e=1,idName=ADA LOVELACE,idNumber=12345678A,idCountry=GB,idExpiry=20251212),
     * SubscriberAddress=(AddressLine=37 Ireland
     * st,City=Auckland,State=Waikato,Country=NZ)
     */

    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CustomerData=%s,IdData=%s,SubscriberAddress=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.customerData, this.idData, this.subscriberAddress);
    }
}
