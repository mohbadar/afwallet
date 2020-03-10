package af.asr.mpaisa.transalator.cashin;

import af.asr.mpaisa.message.cashin.CashInRequest;
import af.asr.mpaisa.message.cashin.CashInResponse;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class CashInJaxbTranslator {

    public String marshalRequest(CashInRequest obj) throws JAXBException {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(CashInRequest.class);

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
//            System.out.println( xmlContent );

            return xmlContent;
    }


    public String marshalResponse(CashInResponse cashInResponse) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(CashInResponse.class);

        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Print XML String to Console
        StringWriter sw = new StringWriter();

        //Write XML to StringWriter
        jaxbMarshaller.marshal(cashInResponse, sw);

        //Verify XML Content
        String xmlContent = sw.toString();
//        System.out.println( xmlContent );

        return xmlContent;
    }

    /**
     * Unmarshal CashInRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public CashInRequest unmarshalRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(CashInRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        CashInRequest request = (CashInRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal CashInResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public CashInResponse unmarshalResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(CashInResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        CashInResponse request = (CashInResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


}
