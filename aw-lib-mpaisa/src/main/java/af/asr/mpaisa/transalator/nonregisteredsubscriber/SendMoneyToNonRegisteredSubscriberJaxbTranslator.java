package af.asr.mpaisa.transalator.nonregisteredsubscriber;

import af.asr.mpaisa.message.merchantpayment.MerchantPaymentSingleStepRequest;
import af.asr.mpaisa.message.merchantpayment.MerchantPaymentSingleStepResponse;
import af.asr.mpaisa.message.nonregisteredsubscriber.SendMoneyToNonRegisteredSubscriberRequest;
import af.asr.mpaisa.message.nonregisteredsubscriber.SendMoneyToNonRegisteredSubscriberResponse;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class SendMoneyToNonRegisteredSubscriberJaxbTranslator {

    /**
     * marshal SendMoneyToNonRegisteredSubscriberRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(SendMoneyToNonRegisteredSubscriberRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(SendMoneyToNonRegisteredSubscriberRequest.class);

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
     * marshal SendMoneyToNonRegisteredSubscriberResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(SendMoneyToNonRegisteredSubscriberResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(SendMoneyToNonRegisteredSubscriberResponse.class);

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
     * Unmarshal SendMoneyToNonRegisteredSubscriberRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public SendMoneyToNonRegisteredSubscriberRequest unmarshalSendMoneyToNonRegisteredSubscriberRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(SendMoneyToNonRegisteredSubscriberRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        SendMoneyToNonRegisteredSubscriberRequest request = (SendMoneyToNonRegisteredSubscriberRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal SendMoneyToNonRegisteredSubscriberResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public SendMoneyToNonRegisteredSubscriberResponse unmarshalSendMoneyToNonRegisteredSubscriberResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(SendMoneyToNonRegisteredSubscriberResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        SendMoneyToNonRegisteredSubscriberResponse request = (SendMoneyToNonRegisteredSubscriberResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

}
