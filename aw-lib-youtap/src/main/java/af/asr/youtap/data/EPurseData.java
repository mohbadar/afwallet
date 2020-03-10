package af.asr.youtap.data;

/**
 * Aggregation of purse information extracted or to be written on the purse card.
 *
 * Example:
 *
 * EPurseData=(EPurseId=39BD361E,EPurseCurrency=IDR,EPurseBalance=95807,EPurseCounter=
 * 7ffffff0)
 */
public class EPurseData {

    private String ePurseId;
    private String ePurseCurrency;
    private String ePurseBalance;
    private String ePurseCounter;


    public EPurseData(Builder builder)
    {
        this.ePurseId = builder.ePurseId;
        this.ePurseCurrency = builder.ePurseCurrency;
        this.ePurseBalance = builder.ePurseBalance;
        this.ePurseCounter = builder.ePurseCounter;
    }


    public static class Builder{


        private String ePurseId;
        private String ePurseCurrency;
        private String ePurseBalance;
        private String ePurseCounter;

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder withEPurseId(String ePurseId)
        {
            this.ePurseId = ePurseId;
            return this;
        }

        public Builder withEPurseCurrency(String ePurseCurrency)
        {
            this.ePurseCurrency = ePurseCurrency;
            return  this;
        }

        public Builder withEPurseBalance(String ePurseBalance)
        {
            this.ePurseBalance = ePurseBalance;
            return  this;
        }

        public Builder withEPurseCounter(String ePurseCounter)
        {
            this.ePurseCounter = ePurseCounter;
            return  this;
        }

        public EPurseData build()
        {
            return new EPurseData(this);
        }

    }

    public String getData()
    {
        return String.format("");
    }
}
