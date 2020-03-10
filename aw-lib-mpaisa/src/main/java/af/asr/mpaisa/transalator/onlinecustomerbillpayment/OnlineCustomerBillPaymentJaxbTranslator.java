package af.asr.mpaisa.transalator.onlinecustomerbillpayment;

import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1ConfirmationResponse;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1InitiationRequest;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1InitiationResponse;
import af.asr.mpaisa.message.nonregisteredsubscriber.SendMoneyToNonRegisteredSubscriberRequest;
import af.asr.mpaisa.message.nonregisteredsubscriber.SendMoneyToNonRegisteredSubscriberResponse;
import af.asr.mpaisa.message.onlinecustomerbillpayment.OnlineCustomerBillPaymentRequest;
import af.asr.mpaisa.message.onlinecustomerbillpayment.OnlineCustomerBillPaymentResponse;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class OnlineCustomerBillPaymentJaxbTranslator {

    /**
     * marshal OnlineCustomerBillPaymentRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(OnlineCustomerBillPaymentRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(OnlineCustomerBillPaymentRequest.class);

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
     * marshal OnlineCustomerBillPaymentResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(OnlineCustomerBillPaymentResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(OnlineCustomerBillPaymentResponse.class);

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
     * Unmarshal OnlineCustomerBillPaymentRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public OnlineCustomerBillPaymentRequest unmarshalOnlineCustomerBillPaymentRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(OnlineCustomerBillPaymentRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        OnlineCustomerBillPaymentRequest request = (OnlineCustomerBillPaymentRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal OnlineCustomerBillPaymentResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public OnlineCustomerBillPaymentResponse unmarshalP2PConfigChoice1InitiationResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(OnlineCustomerBillPaymentResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        OnlineCustomerBillPaymentResponse request = (OnlineCustomerBillPaymentResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

}
