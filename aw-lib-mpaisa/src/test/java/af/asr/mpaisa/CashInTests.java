package af.asr.mpaisa;

import af.asr.mpaisa.message.cashin.CashInRequest;
import af.asr.mpaisa.message.cashin.CashInResponse;
import af.asr.mpaisa.transalator.cashin.CashInJaxbTranslator;
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
public class CashInTests {

    CashInRequest cashInRequest;

    CashInResponse cashInResponse;

    private final static String EXPECTED_CASH_IN_REQUEST_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>RCIREQ</TYPE>\n" +
            "    <MSISDN>9942222</MSISDN>\n" +
            "    <MSISDN2>9942222</MSISDN2>\n" +
            "    <AMOUNT>1000</AMOUNT>\n" +
            "    <IDNO>9942223</IDNO>\n" +
            "    <PIN>1234</PIN>\n" +
            "    <SNDPROVIDER>12</SNDPROVIDER>\n" +
            "    <RCVPROVIDER>101</RCVPROVIDER>\n" +
            "    <SNDINSTRUMENT>0</SNDINSTRUMENT>\n" +
            "    <RCVINSTRUMENT>12</RCVINSTRUMENT>\n" +
            "    <LANGUAGE1>0</LANGUAGE1>\n" +
            "    <LANGUAGE2>0</LANGUAGE2>\n" +
            "</COMMAND>";

    private final static String EXPECTED_CASH_IN_RESPONSE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>RCIRESP</TYPE>\n" +
            "    <TXNID>CI070608.1512.000001</TXNID>\n" +
            "    <TXNSTATUS>200</TXNSTATUS>\n" +
            "    <TRID>32131</TRID>\n" +
            "    <MESSAGE>Cash In Success to 9942222. The details are as follows: transaction amount:\n" +
            "1000, transaction Id: CI070608.1512.000001, charges: 1.00 INR, commission: 0.0 INR, net debit\n" +
            "amount : 6.00 AF, new balance: 88.00 AF.</MESSAGE>\n" +
            "</COMMAND>";

    @Before
    public void init()
    {
        //cash in request message
        cashInRequest = new CashInRequest();
        cashInRequest.setAMOUNT(new BigDecimal(1000));
        cashInRequest.setIDNO("9942223");
        cashInRequest.setLANGUAGE1(0);
        cashInRequest.setLANGUAGE1(0);
        cashInRequest.setMSISDN("9942222");
        cashInRequest.setMSISDN2("9942222");
        cashInRequest.setPIN(1234);
        cashInRequest.setTYPE("RCIREQ");
        cashInRequest.setSNDPROVIDER(101);
        cashInRequest.setSNDPROVIDER(12);
        cashInRequest.setRCVPROVIDER(101);
        cashInRequest.setRCVINSTRUMENT(12);

        //cash in response
        cashInResponse = new CashInResponse();
        cashInResponse.setTYPE("RCIRESP");
        cashInResponse.setTRID("32131");
        cashInResponse.setTXNID("CI070608.1512.000001");
        cashInResponse.setTXNSTATUS(200);
        cashInResponse.setMESSAGE("Cash In Success to 9942222. The details are as follows: transaction amount:\n" +
                "1000, transaction Id: CI070608.1512.000001, charges: 1.00 INR, commission: 0.0 INR, net debit\n" +
                "amount : 6.00 AF, new balance: 88.00 AF.");

    }

    @Test
    public void cashInRequestIsNotNull() throws JAXBException {

        CashInJaxbTranslator transalator = new CashInJaxbTranslator();
        String result = transalator.marshalRequest(cashInRequest);
        assertThat(result).isNotNull();
    }

    @Test
    public void cashInRequestReturnExpectedXmlRequest() throws JAXBException {
        CashInJaxbTranslator transalator = new CashInJaxbTranslator();
        String result = transalator.marshalRequest(cashInRequest);
        assertThat(result.trim()).isEqualToIgnoringCase(EXPECTED_CASH_IN_REQUEST_XML.trim());
    }


    @Test
    public void cashInResponseIsNotNull() throws JAXBException {

        CashInJaxbTranslator transalator = new CashInJaxbTranslator();
        String result = transalator.marshalResponse(cashInResponse);
        assertThat(result).isNotNull();
    }


    @Test
    public void cashInRequestReturnExpectedXmlResponse() throws JAXBException {
        CashInJaxbTranslator transalator = new CashInJaxbTranslator();
        String result = transalator.marshalResponse(cashInResponse);
        assertThat(result.trim()).isEqualToIgnoringCase(EXPECTED_CASH_IN_RESPONSE_XML.trim());
    }


    @Test
    public void cashInRequestXmlToObject() throws JAXBException {
        CashInJaxbTranslator transalator = new CashInJaxbTranslator();
        CashInRequest request = transalator.unmarshalRequest(EXPECTED_CASH_IN_REQUEST_XML);
        assertThat(request).isEqualTo(cashInRequest);
    }

    @Test
    public void cashInResponseXmlToObject() throws JAXBException {
        CashInJaxbTranslator transalator = new CashInJaxbTranslator();
        CashInResponse response = transalator.unmarshalResponse(EXPECTED_CASH_IN_RESPONSE_XML);
        assertThat(response).isEqualTo(cashInResponse);
    }
}
