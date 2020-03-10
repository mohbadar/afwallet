package af.asr.youtap.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a number of threshold amounts and required identification records for a single transaction following the
 * exchange quote. If the transaction amount exceeds the threshold amount the subscriber is prompted to enter the
 * specified ID.
 */

public class AuthData {

    /**
     * Threshold amount above which the ID
     * specified in CTALId needs to be requested.
     */
    private Map<String, Double> CTALAmount;
    private Map<String, Double> CTALId;


    public AuthData(Builder builder)
    {
        this.CTALAmount = builder.CTALAmount;
        this.CTALId = builder.CTALId;
    }

    public static class Builder {

        private Map<String, Double> CTALAmount = new HashMap<>();
        private Map<String, Double> CTALId = new HashMap<>();


        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder addToCTALAmount(String name, Double amount)
        {
            this.CTALAmount.put(name, amount);
            return this;
        }

        public Builder addToCTALId(String name, Double amount)
        {
            this.CTALId.put(name, amount);
            return this;
        }

        public AuthData build()
        {
            return new AuthData(this);
        }
    }

    public String getData()
    {
        return String.format("");
    }


}
