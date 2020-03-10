package af.asr.youtap.message.response;

public class MerchantLoginResponse {

    private String messageType;
    private int status;
    private String customerId;
    private String transactionId;
    private String promoMsg;
    private String merchantName;
    private String loyaltyScheme;
    private String profileTags;
    private String allowedIdTypes;
    private int staffPinEnabled;
    private String createFlags;

    public MerchantLoginResponse(Builder builder){
        this.messageType=builder.messageType;
        this.status = builder.status;
        this.customerId = builder.customerId;
        this.transactionId = builder.transactionId;
        this.promoMsg = builder.promoMsg;
        this.merchantName = builder.merchantName;
        this.loyaltyScheme = builder.loyaltyScheme;
        this.profileTags = builder.profileTags;
        this.allowedIdTypes = builder.allowedIdTypes;
        this.staffPinEnabled = builder.staffPinEnabled;
        this.createFlags = builder.createFlags;
    }

    public static class Builder{

        private String messageType;
        private int status;
        private String customerId;
        private String transactionId;
        private String promoMsg;
        private String merchantName;
        private String loyaltyScheme;
        private String profileTags;
        private String allowedIdTypes;
        private int staffPinEnabled;
        private String createFlags;

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

        public Builder withCustomerId(String customerId){
            this.customerId =customerId;
            return this;
        }

        public Builder withTransactionId(String transactionId){
            this.transactionId =transactionId;
            return this;
        }


        public Builder withPromoMsg(String promoMsg){
            this.promoMsg =promoMsg;
            return this;
        }

        public Builder withMerchantName(String merchantName){
            this.merchantName =merchantName;
            return this;
        }

        public Builder withLoyaltyScheme(String loyaltyScheme){
            this.loyaltyScheme =loyaltyScheme;
            return this;
        }

        public Builder withProfileTags(String profileTags){
            this.profileTags =profileTags;
            return this;
        }

        public Builder withAllowedIdTypes(String allowedIdTypes){
            this.allowedIdTypes =allowedIdTypes;
            return this;
        }

        public Builder withStaffPinEnabled(int staffPinEnabled){
            this.staffPinEnabled =staffPinEnabled;
            return this;
        }

        public Builder withCreateFlags(String createFlags){
            this.createFlags =createFlags;
            return this;
        }

        public MerchantLoginResponse build()
        {
            return new MerchantLoginResponse(this);
        }

    }


    public String getMessage()
    {
        return String.format("Status=%d,CustomerId=%s,PromoMsg=%s,TransactionId=%s,ProfileTags=%s,StaffPinEnabled=%d,CreateFlags=%s,AllowedIdType=%s,MessageType=%s", this.status, this.customerId, this.promoMsg, this.transactionId, this.profileTags, this.staffPinEnabled, this.createFlags, this.allowedIdTypes, this.messageType);
    }
}
