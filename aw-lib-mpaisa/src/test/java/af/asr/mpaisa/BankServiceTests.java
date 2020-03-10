package af.asr.mpaisa;


import af.asr.mpaisa.message.bankservice.BankToWalletRequest;
import af.asr.mpaisa.message.bankservice.BankToWalletResponse;
import af.asr.mpaisa.message.bankservice.WalletToBankRequest;
import af.asr.mpaisa.message.bankservice.WalletToBankResponse;
import af.asr.mpaisa.transalator.bankservice.BankServiceJaxbTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BankServiceTests {

    private WalletToBankRequest walletToBankRequest;
    private WalletToBankResponse walletToBankResponse;
    private BankToWalletRequest bankToWalletRequest;
    private BankToWalletResponse bankToWalletResponse;


    private static final String EXPECTED_WALLET_TO_BANK_REQUEST ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>CWBREQ</TYPE>\n" +
            "    <MSISDN>9942222</MSISDN>\n" +
            "    <MSISDN2>9942222</MSISDN2>\n" +
            "    <ACCNO2>9942222</ACCNO2>\n" +
            "    <AMOUNT>1000</AMOUNT>\n" +
            "    <PROVIDER>121</PROVIDER>\n" +
            "    <PAYID>0</PAYID>\n" +
            "    <PROVIDER2>213</PROVIDER2>\n" +
            "    <BANKID>234</BANKID>\n" +
            "    <PIN>1234</PIN>\n" +
            "    <LANGUAGE1>0</LANGUAGE1>\n" +
            "</COMMAND>";

    private static final String EXPECTED_WALLET_TO_BANK_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>CWBRESP</TYPE>\n" +
            "    <TXNID>XX121126.1821.C00002</TXNID>\n" +
            "    <TXNSTATUS>200</TXNSTATUS>\n" +
            "    <MESSAGE>Your wallet to bank account transfer is done.</MESSAGE>\n" +
            "</COMMAND>";

    private static final String EXPECTED_BANK_TO_WALLET_REQUEST ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>CBWREQ</TYPE>\n" +
            "    <MSISDN>9942222</MSISDN>\n" +
            "    <MSISDN2>9942223</MSISDN2>\n" +
            "    <ACCNO>7637463</ACCNO>\n" +
            "    <PROVIDER>101</PROVIDER>\n" +
            "    <BANKID>21341</BANKID>\n" +
            "    <PAYID2>12</PAYID2>\n" +
            "    <PROVIDER2>102</PROVIDER2>\n" +
            "    <AMOUNT>1000</AMOUNT>\n" +
            "    <PIN>1234</PIN>\n" +
            "    <LANGUAGE>0</LANGUAGE>\n" +
            "</COMMAND>";


    private static final String EXPECTED_BANK_TO_WALLET_RESPONSE ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>CBWRESP</TYPE>\n" +
            "    <TXNID>XX121126.1821.C00002</TXNID>\n" +
            "    <TXNSTATUS>200</TXNSTATUS>\n" +
            "    <MESSAGE>Your bank to wallet account transfer is done.</MESSAGE>\n" +
            "</COMMAND>";
    @Before
    public void init()
    {
        walletToBankRequest = new WalletToBankRequest();
        walletToBankRequest.setTYPE("CWBREQ");
        walletToBankRequest.setMSISDN("9942222");
        walletToBankRequest.setMSISDN2("9942222");
        walletToBankRequest.setACCNO2("9942222");
        walletToBankRequest.setAMOUNT(new BigDecimal(1000));
        walletToBankRequest.setBANKID("234");
        walletToBankRequest.setLANGUAGE1(0);
        walletToBankRequest.setPROVIDER(121);
        walletToBankRequest.setPROVIDER2(213);
        walletToBankRequest.setPIN(1234);


        walletToBankResponse = new WalletToBankResponse();
        walletToBankResponse.setTYPE("CWBRESP");
        walletToBankResponse.setMESSAGE("Your wallet to bank account transfer is done.");
        walletToBankResponse.setTXNID("XX121126.1821.C00002");
        walletToBankResponse.setTXNSTATUS(200);

        bankToWalletRequest = new BankToWalletRequest();
        bankToWalletRequest.setTYPE("CBWREQ");
        bankToWalletRequest.setACCNO("7637463");
        bankToWalletRequest.setAMOUNT(new BigDecimal(1000));
        bankToWalletRequest.setMSISDN("9942222");
        bankToWalletRequest.setMSISDN2("9942223");
        bankToWalletRequest.setPIN(1234);
        bankToWalletRequest.setBANKID("21341");
        bankToWalletRequest.setPROVIDER(101);
        bankToWalletRequest.setPROVIDER2(102);
        bankToWalletRequest.setPAYID2(12);

        bankToWalletResponse = new BankToWalletResponse();
        bankToWalletResponse.setTYPE("CBWRESP");
        bankToWalletResponse.setMESSAGE("Your bank to wallet account transfer is done.");
        bankToWalletResponse.setTXNID("XX121126.1821.C00002");
        bankToWalletResponse.setTXNSTATUS(200);

    }


    //Wallet To Bank
    @Test
    public void walletToBankRequestObjectIsNotNull() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshalRequest(walletToBankRequest);
        assertThat(result).isNotNull();
    }

    @Test
    public void walletToBankRequestObjectTranslatorStringIsEquelToExpectedString() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshalRequest(walletToBankRequest);
        assertThat(result.trim()).isEqualTo(EXPECTED_WALLET_TO_BANK_REQUEST.trim());
    }

    @Test
    public void xmlContentStringShouldBuildAWalletToBankRequestObject() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        WalletToBankRequest result = translator.unmarshalWalletToBankRequest(EXPECTED_WALLET_TO_BANK_REQUEST);
        assertThat(result).isEqualTo(walletToBankRequest);
    }


    @Test
    public void walletToBankResponseObjectIsNotNull() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshaResponse(walletToBankResponse);
        assertThat(result).isNotNull();
    }

    @Test
    public void walletToBankResponseObjectTranslatorStringIsEquelToExpectedString() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshaResponse(walletToBankResponse);
        assertThat(result.trim()).isEqualTo(EXPECTED_WALLET_TO_BANK_RESPONSE.trim());
    }

    @Test
    public void xmlContentStringShouldBuildAWalletToBankResponseObject() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        WalletToBankResponse result = translator.unmarshalWalletToBankResponse(EXPECTED_WALLET_TO_BANK_RESPONSE);
        assertThat(result).isEqualTo(walletToBankResponse);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ///Bank to Wallet

    @Test
    public void bankToWalletRequestObjectIsNotNull() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshalRequest(bankToWalletRequest);
        assertThat(result).isNotNull();
    }

    @Test
    public void bankToWalletRequestObjectTranslatorStringIsEquelToExpectedString() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshalRequest(bankToWalletRequest);
        assertThat(result.trim()).isEqualTo(EXPECTED_BANK_TO_WALLET_REQUEST.trim());
    }

    @Test
    public void xmlContentStringShouldBuildAbankToWalletRequestObject() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        BankToWalletRequest result = translator.unmarshalBankToWalletRequest(EXPECTED_BANK_TO_WALLET_REQUEST);
        assertThat(result).isEqualTo(bankToWalletRequest);
    }


    @Test
    public void bankToWalletResponseObjectIsNotNull() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshaResponse(bankToWalletResponse);
        assertThat(result).isNotNull();
    }

    @Test
    public void bankToWalletResponseObjectTranslatorStringIsEquelToExpectedString() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        String result  = translator.marshaResponse(bankToWalletResponse);
        assertThat(result.trim()).isEqualTo(EXPECTED_BANK_TO_WALLET_RESPONSE.trim());
    }

    @Test
    public void xmlContentStringShouldBuildAbankToWalletResponseObject() throws JAXBException {
        BankServiceJaxbTranslator translator = new BankServiceJaxbTranslator();
        BankToWalletResponse result = translator.unmarshalBankToWalletResponse(EXPECTED_BANK_TO_WALLET_RESPONSE);
        assertThat(result).isEqualTo(bankToWalletResponse);
    }

}
