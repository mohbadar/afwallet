package af.asr.youtap.data;

public class TerminalStatusData {

    /**
     * Client device’s battery level if appropriate
     * as a percentage of the fully charged level
     */
    private int batteryLevel;

    /**
     * Average number of milliseconds for a client
     * device to send and receive a response
     * between Ping commands
     */
    private double averageMessageRoundTrip;

    /**
     * Maximum round trip in milliseconds during
     * the last ping message period
     */
    private double maxMessageRoundTrip;

    /**
     * Minimun round trip in milliseconds during
     * the last ping message period
     */
    private double minMessageRoundTrip;


    /**
     * The Client device’s radio modem signal
     * strength as a percentage at the time of
     * sending the terminal data
     */
    private int signalStrength;


    /**
     * Another identifier for the customer, used in the context of
     * smart mobile phone application
     */
    private String imsi;

    /**
     * International mobile equipment
     * identity number
     */
    private String imei;


    /**
     * Service provider for the SIM card in the
     * client device
     */
    private String serviceOperator;


    /**
     * SIM card identification number inserted
     * into the client device
     */
    private String simId;


    private TerminalStatusData(Builder builder)
    {
        this.batteryLevel = builder.batteryLevel;
        this.averageMessageRoundTrip = builder.averageMessageRoundTrip;
        this.maxMessageRoundTrip = builder.maxMessageRoundTrip;
        this.minMessageRoundTrip = builder.minMessageRoundTrip;
        this.signalStrength = builder.signalStrength;
        this.imsi = builder.imsi;
        this.imei = builder.imei;
        this.simId  =builder.simId;
        this.serviceOperator = builder.serviceOperator;
    }

    public static class Builder {

        /**
         * Client device’s battery level if appropriate
         * as a percentage of the fully charged level
         */
        private int batteryLevel;

        /**
         * Average number of milliseconds for a client
         * device to send and receive a response
         * between Ping commands
         */
        private double averageMessageRoundTrip;

        /**
         * Maximum round trip in milliseconds during
         * the last ping message period
         */
        private double maxMessageRoundTrip;

        /**
         * Minimun round trip in milliseconds during
         * the last ping message period
         */
        private double minMessageRoundTrip;


        /**
         * The Client device’s radio modem signal
         * strength as a percentage at the time of
         * sending the terminal data
         */
        private int signalStrength;


        /**
         * Another identifier for the customer, used in the context of
         * smart mobile phone application
         */
        private String imsi;

        /**
         * International mobile equipment
         * identity number
         */
        private String imei;


        /**
         * Service provider for the SIM card in the
         * client device
         */
        private String serviceOperator;


        /**
         * SIM card identification number inserted
         * into the client device
         */
        private String simId;


        public static Builder newInstance()
        {
            return new Builder();
        }


        public Builder withBatteryLevel(int batteryLevel)
        {
            this.batteryLevel = batteryLevel;
            return this;
        }

        public Builder withAverageMessageRoundTrip(double averageMessageRoundTrip)
        {
            this.averageMessageRoundTrip = averageMessageRoundTrip;
            return this;
        }

        public Builder withMaxMessageRoundTrip(double maxMessageRoundTrip)
        {
            this.maxMessageRoundTrip = this.maxMessageRoundTrip;
            return this;
        }

        public Builder withMinMessageRoundTrip(double minMessageRoundTrip)
        {
            this.minMessageRoundTrip = minMessageRoundTrip;
            return this;
        }
        public Builder withSignalStrength(int signalStrength)
        {
            this.signalStrength = signalStrength;
            return this;
        }

        public Builder withIMSI(String imsi)
        {
            this.imsi =imsi;
            return this;
        }

        public Builder withIMEI(String imei)
        {
            this.imei = imei;
            return this;
        }

        public Builder withServiceOperator(String serviceOperator)
        {
            this.serviceOperator = serviceOperator;
            return this;
        }

        public TerminalStatusData build()
        {
            return new TerminalStatusData(this);
        }

    }

    public String getData()
    {
        return String.format("");
    }

}
