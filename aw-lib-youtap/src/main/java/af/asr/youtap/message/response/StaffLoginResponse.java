package af.asr.youtap.message.response;

public class StaffLoginResponse {

    private String messageType;
    private int status;
    private String transactionId;
    private String profileTags;


    public StaffLoginResponse(Builder builder)
    {
        this.messageType = builder.messageType;
        this.status = builder.status;
        this.transactionId = builder.transactionId;
        this.profileTags = builder.transactionId;
    }

    public  static class Builder{

        private String messageType;
        private int status;
        private String transactionId;
        private String profileTags;

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder withMessageType(String messageType){
            this.messageType =messageType;
            return this;
        }

        public Builder withStatus(int status){
            this.status =status;
            return this;
        }


        public Builder withTransactionId(String transactionId){
            this.transactionId =transactionId;
            return this;
        }

        public Builder withProfileTags(String profileTags){
            this.profileTags =profileTags;
            return this;
        }

        public StaffLoginResponse build(){
            return new StaffLoginResponse(this);
        }

    }

    public String getMessage()
    {
        return String.format("Status=%d,TransactionId=%s,ProfileTags=%s,MessageType=%s", this.status,  this.transactionId, this.profileTags,this.messageType);
    }

}
