package af.asr.mpaisa;


import af.asr.mpaisa.message.cashout.CashOutRequest;
import af.asr.mpaisa.message.cashout.CashoutResponse;
import af.asr.mpaisa.transalator.cashout.CashOutJaxbTranslator;
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
public class CashOutTests {

    CashOutRequest request;

    CashoutResponse response;

    private final static String EXPECTED_REQUEST_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>RCOREQ</TYPE>\n" +
            "    <MSISDN>9942222</MSISDN>\n" +
            "    <MSISDN2>9942222</MSISDN2>\n" +
            "    <PROVIDER>101</PROVIDER>\n" +
            "    <PAYID>12</PAYID>\n" +
            "    <PROVIDER2>101</PROVIDER2>\n" +
            "    <PAYID2>0</PAYID2>\n" +
            "    <AMOUNT>1000</AMOUNT>\n" +
            "    <IDNO>9942223</IDNO>\n" +
            "    <PIN>1234</PIN>\n" +
            "    <LANGUAGE1>0</LANGUAGE1>\n" +
            "    <LANGUAGE2>0</LANGUAGE2>\n" +
            "</COMMAND>";

    private final static String EXPECTED_RESPONSE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMMAND>\n" +
            "    <TYPE>RCORESP</TYPE>\n" +
            "    <TXNID>CI070608.1512.000001</TXNID>\n" +
            "    <TXNSTATUS>200</TXNSTATUS>\n" +
            "    <MESSAGE>Cash In Success to 9942222. The details are as follows: transaction amount:\n" +
            "1000, transaction Id: CI070608.1512.000001, charges: 1.00 INR, commission: 0.0 INR, net debit\n" +
            "amount : 6.00 AF, new balance: 88.00 AF.</MESSAGE>\n" +
            "</COMMAND>";

    @Before
    public void init()
    {
        //cash in request message
        request = new CashOutRequest();
        request.setAMOUNT(new BigDecimal(1000));
        request.setIDNO(9942223);
        request.setMSISDN("9942222");
        request.setMSISDN2("9942222");
        request.setPIN(1234);
        request.setTYPE("RCOREQ");
        request.setLANGUAGE1(0);
        request.setLANGUAGE2(0);
        request.setPROVIDER(101);
        request.setPAYID(12);
        request.setPROVIDER2(101);
        request.setPAYID(12);


        //response
        response = new CashoutResponse();
        response.setTYPE("RCORESP");
        response.setTXNID("CI070608.1512.000001");
        response.setTXNSTATUS(200);
        response.setMESSAGE("Cash In Success to 9942222. The details are as follows: transaction amount:\n" +
                "1000, transaction Id: CI070608.1512.000001, charges: 1.00 INR, commission: 0.0 INR, net debit\n" +
                "amount : 6.00 AF, new balance: 88.00 AF.");

    }

    @Test
    public void requestIsNotNull() throws JAXBException {

        CashOutJaxbTranslator transalator = new CashOutJaxbTranslator();
        String result = transalator.marshalCashOutRequest(request);
        assertThat(result).isNotNull();
    }

    @Test
    public void requestReturnExpectedXmlRequest() throws JAXBException {
        CashOutJaxbTranslator transalator = new CashOutJaxbTranslator();
        String result = transalator.marshalCashOutRequest(request);
        assertThat(result.trim()).isEqualToIgnoringCase(EXPECTED_REQUEST_XML.trim());
    }


    @Test
    public void responseIsNotNull() throws JAXBException {

        CashOutJaxbTranslator transalator = new CashOutJaxbTranslator();
        String result = transalator.marshalCashOutResponse(response);
        assertThat(result).isNotNull();
    }


    @Test
    public void requestReturnExpectedXmlResponse() throws JAXBException {
        CashOutJaxbTranslator transalator = new CashOutJaxbTranslator();
        String result = transalator.marshalCashOutResponse(response);
        assertThat(result.trim()).isEqualToIgnoringCase(EXPECTED_RESPONSE_XML.trim());
    }


    @Test
    public void requestXmlToObject() throws JAXBException {
        CashOutJaxbTranslator transalator = new CashOutJaxbTranslator();
        CashOutRequest request = transalator.unmarshalRequest(EXPECTED_REQUEST_XML);
        assertThat(request).isEqualTo(request);
    }

    @Test
    public void responseXmlToObject() throws JAXBException {
        CashOutJaxbTranslator transalator = new CashOutJaxbTranslator();
        CashoutResponse response = transalator.unmarshalResponse(EXPECTED_RESPONSE_XML);
        assertThat(response).isEqualTo(response);
    }
}
