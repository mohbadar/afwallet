package af.asr.youtap.message.response;

public class MiniStatementResponse {

    private String messageType;
    private int status;
    private String transactionId;
    /**
     * Number of blocks that makes up the data
     * to be sent to the client device ( for the mini
     * statement)
     */
    private int blockCount;
    /**
     * A list of appropriately formatted items for
     * display purposes separated by the field
     * separator character (pipe) ‘|’
     */
    private String dspData;
    /**
     * A list of appropriately formatted items for
     * printing purposes separated by the field
     * separator character (pipe) ‘|’
     */
    private String prnData;



    public MiniStatementResponse(Builder builder)
    {
        this.messageType = builder.messageType;
        this.status = builder.status;
        this.transactionId = builder.transactionId;
        this.blockCount = builder.blockCount;
        this.dspData = builder.dspData;
        this.prnData = builder.prnData;
    }

    public static class Builder {


        private String messageType;
        private int status;
        private String transactionId;
        private int blockCount;
        private String dspData;
        private String prnData;


        public static  Builder newInstance()
        {
            return new Builder();
        }


        public Builder withMessageType(String messageType)
        {
            this.messageType = messageType;
            return this;
        }

        public Builder withStatus(int status)
        {
            this.status = status;
            return this;
        }

        public Builder withTransactionId(String transactionId)
        {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withBlockCount(int blockCount)
        {
            this.blockCount = blockCount;
            return this;
        }

        public Builder withDspData(String dspData)
        {
            this.dspData = dspData;
            return this;
        }

        public Builder withPrnData(String prnData)
        {
            this.prnData = prnData;
            return this;
        }

        public MiniStatementResponse build()
        {
            return new MiniStatementResponse(this);
        }

    }

    public String getMessage()
    {
        return String.format("Status=%d,TransactionId=%s,BlockCount=%d,PrnData=%s,MessageType=%s", this.status, this.transactionId, this.blockCount, this.prnData, this.messageType);
    }
}
