package af.asr.mpaisa.transalator.cashout;

import af.asr.mpaisa.message.cashin.CashInRequest;
import af.asr.mpaisa.message.cashin.CashInResponse;
import af.asr.mpaisa.message.cashout.CashOutRequest;
import af.asr.mpaisa.message.cashout.CashoutResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class CashOutJaxbTranslator {

    public String marshalCashOutRequest(CashOutRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(CashOutRequest.class);

        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Print XML String to Console
        StringWriter sw = new StringWriter();

        //Write XML to StringWriter
        jaxbMarshaller.marshal(obj, sw);

        //Verify XML Content
        String xmlContent = sw.toString();
            System.out.println( xmlContent );

        return xmlContent;
    }


    public String marshalCashOutResponse(CashoutResponse cashoutResponse) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(CashoutResponse.class);

        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Print XML String to Console
        StringWriter sw = new StringWriter();

        //Write XML to StringWriter
        jaxbMarshaller.marshal(cashoutResponse, sw);

        //Verify XML Content
        String xmlContent = sw.toString();
        System.out.println( xmlContent );

        return xmlContent;
    }


    /**
     * Unmarshal CashOutRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public CashOutRequest unmarshalRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(CashOutRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        CashOutRequest request = (CashOutRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal CashoutResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public CashoutResponse unmarshalResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(CashoutResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        CashoutResponse request = (CashoutResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


}
