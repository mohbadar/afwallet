package af.asr.youtap.data;

public class CustomerSearchData {


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
     * Mobile money account PIN
     */
    private int mobMonPin;

    /**
     * Another identifier for the customer, used in the context of
     * smart mobile phone application
     *
     * Subscriber Identity number
     */
    private String imsi;

    private CustomerSearchData (Builder builder)
    {
        this.NFCTagId = builder.NFCTagId;
        this.givenName = builder.givenName;
        this.surename = builder.surename;
        this.dob = builder.dob;
        this.customerId = builder.customerId;
        this.msisdn = builder.msisdn;
        this.cardId = builder.cardId;
        this.cardExpiry = builder.cardExpiry;
        this.mobMonPin = builder.mobMonPin;
        this.imsi = builder.imsi;
    }

    public static class Builder {

        private String NFCTagId;
        private String givenName;
        private String surename;
        public String dob;
        private String customerId;
        private String msisdn;
        private String cardId;
        private String cardExpiry;
        private int mobMonPin;
        private String imsi;


        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder withNFCTagId(String NFCTagId)
        {
            this.NFCTagId = NFCTagId;
            return  this;
        }

        public Builder withGivenName(String givenName)
        {
            this.givenName = givenName;
            return this;
        }

        public Builder withSureName(String sureName)
        {
            this.surename = sureName;
            return this;
        }

        public Builder withCustomerId(String customerId)
        {
            this.customerId = customerId;
            return this;
        }

        public Builder withMSISDN(String msisdn)
        {
            this.msisdn = msisdn;
            return this;
        }

        public Builder withCardId(String cardId)
        {
            this.cardId = cardId;
            return this;
        }

        public Builder withCardExpiry(String cardExpiry)
        {
            this.cardExpiry = cardExpiry;
            return this;
        }

        public Builder withMobMonPin(int mobMonPin){
            this.mobMonPin = mobMonPin;
            return this;
        }

        public Builder withIMSI(String imsi)
        {
            this.imsi = imsi;
            return this;
        }

        public CustomerSearchData build()
        {
            return new CustomerSearchData(this);
        }

    }

    public String getData()
    {
        return String.format("");
    }

}
