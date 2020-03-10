package af.asr.mpaisa.transalator.domestic_money_transfer;

import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1ConfirmationRequest;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1ConfirmationResponse;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1InitiationRequest;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1InitiationResponse;
import af.asr.mpaisa.message.domestic_money_transfer.choice2.P2PConfigChoice2Request;
import af.asr.mpaisa.message.domestic_money_transfer.choice2.P2PConfigChoice2Response;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class DomesticMoneyTransferChoice2JaxbTranslator {


    /**
     * marshal P2PConfigChoice2Request
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(P2PConfigChoice2Request obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(P2PConfigChoice2Request.class);

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

    /**
     * marshal P2PConfigChoice2Response
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(P2PConfigChoice2Response response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(P2PConfigChoice2Response.class);

        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Print XML String to Console
        StringWriter sw = new StringWriter();

        //Write XML to StringWriter
        jaxbMarshaller.marshal(response, sw);

        //Verify XML Content
        String xmlContent = sw.toString();
//        System.out.println( xmlContent );

        return xmlContent;
    }


    /**
     * Unmarshal P2PConfigChoice2Request
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public P2PConfigChoice2Request unmarshalP2PConfigChoice2Request(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(P2PConfigChoice2Request.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        P2PConfigChoice2Request request = (P2PConfigChoice2Request) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal P2PConfigChoice2Response
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public P2PConfigChoice2Response unmarshalP2PConfigChoice1InitiationResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(P2PConfigChoice2Response.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        P2PConfigChoice2Response request = (P2PConfigChoice2Response) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

}
