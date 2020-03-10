package af.asr.youtap;


import af.asr.youtap.message.request.MerchantLoginRequest;
import af.asr.youtap.message.request.StaffLoginRequest;
import af.asr.youtap.message.response.MerchantLoginResponse;
import af.asr.youtap.type.YoutapMessageTypes;

public class TestApp {

    public static void main(String[] args)
    {
        MerchantLoginRequest request = MerchantLoginRequest.Builder.newInstance()
                                        .withMerchantId("86637")
                                        .withMessageType(YoutapMessageTypes.MERCHANT_LOGIN_REQUEST)
                                        .withMerchantPin("1234")
                                        .withTerminalId("21908856")
                                        .withTransactionId("0000000955")
                                        .build();
        System.out.println("Login Request: "+ request.getMessage());


        MerchantLoginResponse response = MerchantLoginResponse.Builder.newInstance()
                .withStatus(0)
                .withCustomerId("86637")
                .withPromoMsg("Youtap the way to pay!")
                .withTransactionId("0000000319")
                .withProfileTags("MenuA=0000,MenuB=000F,MenuC=007F,MenuD=001F,MenuE=003F,MenuF=01FF,MenuG=000F)")
                .withStaffPinEnabled(1)
                .withCreateFlags("0001")
                .withAllowedIdTypes("7")
                .withMessageType(YoutapMessageTypes.MERCHANT_LOGIN_RESPONSE)
                .build();

        System.out.println("Login Response: "+ response.getMessage());


        StaffLoginRequest staffLoginRequest = StaffLoginRequest.Builder.newInstance()
                .withMerchantId("86637")
                .withMessageType(YoutapMessageTypes.STAFF_LOGIN_REQUEST)
                .withStaffPin("1234")
                .withTerminalId("21908856")
                .withTransactionId("0000000955")
                .build();
        System.out.println("Staff Login Request: "+ staffLoginRequest.getMessage());
    }
}
