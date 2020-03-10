package af.asr.youtap.data;

public class CustomerIdentificationData {

    /**
     * /**
     *  * Supported ID types are:
     *  * 1 – Passport
     *  * 2 – Drivers Licence
     *  * 3 – Tazkira ID
     *  * 4 – National ID
     *  * 5 - Taxation
     *
     */
    private String idType;
    /**
     * Date on which the ID expires in format
     * YYYYMMDD.
     */
    private String idExpiry;
    /**
     * Date on which the ID was issued in format
     * YYYYMMDD. This is currently mostly used
     * for ID type 2
     */
    private String idIssue;

    /**
     * Number corresponding to the ID type.
     * D type 1 - Passport number.
     * ID type 2 - Drivers Licence number
     * ID type 3 - Tazkira details which is an
     * aggregation of ID number/Logbook
     * No/Page no/Registration No. E.g.
     * 153206/5/25/90
     */
    private String idNumber;

    /**
     * Name of the organization that issued the
     * ID
     */
    private String idIssuerName;

    /**
     * 2 character ISO country code where the ID
     * was issued
     */
    private String idCountry;

    /**
     * ID Name
     */
    private String idName;


    public CustomerIdentificationData(Builder builder)
    {
        this.idType = builder.idType;
        this.idName = builder.idName;
        this.idCountry = builder.idCountry;
        this.idIssue = builder.idIssue;
        this.idIssuerName = builder.idIssuerName;
        this.idNumber = builder.idNumber;
        this.idExpiry = builder.idExpiry;
    }

    public static class Builder{


        /**
         * /**
         *  * Supported ID types are:
         *  * 1 – Passport
         *  * 2 – Drivers Licence
         *  * 3 – Tazkira ID
         *  * 4 – National ID
         *  * 5 - Taxation
         *
         */
        private String idType;
        /**
         * Date on which the ID expires in format
         * YYYYMMDD.
         */
        private String idExpiry;
        /**
         * Date on which the ID was issued in format
         * YYYYMMDD. This is currently mostly used
         * for ID type 2
         */
        private String idIssue;

        /**
         * Number corresponding to the ID type.
         * D type 1 - Passport number.
         * ID type 2 - Drivers Licence number
         * ID type 3 - Tazkira details which is an
         * aggregation of ID number/Logbook
         * No/Page no/Registration No. E.g.
         * 153206/5/25/90
         */
        private String idNumber;

        /**
         * Name of the organization that issued the
         * ID
         */
        private String idIssuerName;

        /**
         * 2 character ISO country code where the ID
         * was issued
         */
        private String idCountry;

        /**
         * ID Name
         */
        private String idName;



        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder withIdType(String idType)
        {
            this.idType = idType;
            return this;
        }


        public Builder withIdName(String idName)
        {
            this.idName = idName;
            return this;
        }




        public Builder withIdIssue(String idIssue)
        {
            this.idIssue = idIssue;
            return this;
        }




        public Builder withIdIssuerName(String idIssuerName)
        {
            this.idIssuerName = idIssuerName;
            return this;
        }

        public Builder withIdCountry(String idCountry)
        {
            this.idCountry = idCountry;
            return this;
        }

        public Builder withIdExpiry(String idExpiry)
        {
            this.idExpiry = idExpiry;
            return this;
        }

        public Builder withIdNumber(String idNumber)
        {
            this.idNumber = idNumber;
            return this;
        }

        public CustomerIdentificationData build()
        {
            return new CustomerIdentificationData(this);
        }
    }

    public String getMessage()
    {
        return String.format("");
    }

}
