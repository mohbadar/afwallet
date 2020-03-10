package af.asr.youtap.data;

import af.asr.youtap.type.CustomerType;
import af.asr.youtap.type.LinkType;
import lombok.Data;

@Data
public class CustomerData {

    /**
     * Subscribers bank account number that
     * needs to be linked/unlinked from their
     * mobile money account.
     */
    private String bankAccountNo;

    /**
     * Mobile money account PIN
     */
    private int mobMonPin;

    /**
     * Bank account PIN number. In production
     * systems this is encrypted within a pinblock
     */
    private int bankPin;

    /**
     * Subscriber email address
     */
    private String emailAddress;

    /**
     * Subscriber residential address
     */
    private String residentialAddress;

    /**
     * Subscriber residential state
     */
    private String state;

    /**
     * Subscriber residential city
     */
    private  String city;

    /**
     * Subscriber residential country
     */
    private String country;

    /**
     * NFC Card serial number - Hex
     */
    private String NFCTagId;

    /**
     * Subscribers first name
     */
    private String givenName;

    /**
     * Subscribers surname
     */
    private String surename;

    /**
     * Date of birth in YYYYMMDD format
     */
    public String dob;

    /**
     * Subscribers ID number created during
     * registration
     */
    private String customerId;

    /**
     * Mobile phone number
     */
    private String msisdn;

    /**
     * Magnetic card track 2 account number. 16
     * to 19 digits
     */
    private String cardId;

    /**
     * Magnetic card track 2 expiry date
     */
    private String cardExpiry;

    /**
     * contact phone for subscriber
     */
    private String contactPhone;
    /**
     * Customer Type
     */
    private CustomerType customerType;

    /**
     * Subscriber ID verified or not
     *
     * Fixed values are: ‘true’ or ‘false’ and are
     * returned in the CustomerCreateResp
     */
    private  String idVarified;

    /**
     * Customer Gender (M or F)
     */
    private String gender;
    /**
     * New mobile money account PIN for a PIN
     * change. In production systems this is
     * encrypted within a pinblock
     *
     * New mobile money account PIN
     */
    private int newMobMonPin;

    /**
     * First additional finger print data
     *
     *First additional finger print data in
     * BCT_ISO_FMR f ormat
     *
     * HEX Format
     */
    private String fingerData1;

    /**
     * Second additional finger print data in
     * BCT_ISO_FMR f ormat
     *
     * Hex Format
     */
    private  String fingerData2;

    /**
     * Third additional finger print data in
     * BCT_ISO_FMR f ormat
     *
     * Hex Format
     */
    private  String fingerData3;


    /**
     * Links or unlinks a bank account from
     * mobile money account
     * ‘L’ – link account
     * ‘U’ – Unlink account
     */
    private LinkType linkType;

    private CustomerData(Builder builder)
    {
        
    }

    public static class Builder {




    }


    public String getCustomerData()
    {
        return String.format("BankAccountNo=%s, MobMonPin=%d, BankPin=%d, EmailAddress=%s, ResidentialAddress=%s, State=%s, City=%s, Country=%s, NFCTagId=%sو GivenName=%s, Surname=%s, DOB=%s, CustomerId=%s, MSISDN=%s, CardId=%s, CardExpiry=%s, ContactPhone=%s, Gender=%s, CustomerType=%sو IdVerified=%s, NewMobMonPin=%s, FingerData1=%s, FingerData2=%s, FingerData3=%s, LinkType=%s", this.bankAccountNo, this.mobMonPin, this.bankPin, this.emailAddress, this.residentialAddress, this.state, this.city, this.country, this.NFCTagId, this.givenName, this.surename, this.dob, this.customerId, this.msisdn, this.cardId, this.cardExpiry, this.contactPhone, this.gender, this.customerType, this.idVarified, this.newMobMonPin, this.fingerData1, this.fingerData2, this.fingerData3, this.linkType);
    }

}
