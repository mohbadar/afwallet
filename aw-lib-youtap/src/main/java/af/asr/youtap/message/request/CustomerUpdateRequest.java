package af.asr.youtap.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * This message is used when a customer needs to correct or change their details.
 */
public class CustomerUpdateRequest {

    private String messageType;
    private String terminalId;
    private String merchantId;
    private String transactionId;
    private String customerData;
    private String idData;
    private String fingerData;

    /**
     * Request Example:
     * MessageType=CustomerUpdate,TransactionId=0000000011,TerminalId=98944138,MerchantId=
     * 021333333,CustomerData=(GivenName=M,SurName=E,DOB=19720403,MSISDN=64211773279,Email
     * Address=ME@YOUTAP.COM,CustomerId=64211773279),FingerData=464D520020323000000000FC00
     * 0000C0010E00C800C8010002085525407A0019D40040410026230040590026D200404E0029BE0040560
     * 029BD0040C2002CCF004045002EC1004065002EDB0040380035CB00405A0035C8004068003ACF0040AA
     * 007D390080CD0081340040AC008A3200805900914300409400A1380080E200A1B800804C00A24E00405
     * D00A24800406600A83C00406200AA4200405D00AD5400405000BC5E00808900C5A600403D00CA600080
     * 6D00DD860080B200DE2C00406500E27800806D00E47600408900E89D0040A600E8A900806E00EA23004
     * 08000FD260040AE0100B80040850102860080B9010CC50080C501112E000000
     * @return
     */
    public String getMessage()
    {
        return String.format("MessageType=%s,TransactionId=%s,TerminalId=%s,MerchantId=%s,CustomerData=%s,FingerData=%s", this.messageType, this.transactionId, this.terminalId, this.merchantId, this.customerData, this.fingerData);
    }
}
