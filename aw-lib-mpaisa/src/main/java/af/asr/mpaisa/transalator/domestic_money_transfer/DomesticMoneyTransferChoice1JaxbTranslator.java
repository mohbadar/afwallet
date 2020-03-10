package af.asr.mpaisa.transalator.domestic_money_transfer;

import af.asr.mpaisa.message.bankservice.BankToWalletRequest;
import af.asr.mpaisa.message.bankservice.BankToWalletResponse;
import af.asr.mpaisa.message.billpayment.OnlineBillPaymentResponse;
import af.asr.mpaisa.message.billpayment.StandardBillPaymentRequest;
import af.asr.mpaisa.message.billpayment.StandartBillPaymentResponse;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1ConfirmationRequest;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1ConfirmationResponse;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1InitiationRequest;
import af.asr.mpaisa.message.domestic_money_transfer.choice1.P2PConfigChoice1InitiationResponse;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class DomesticMoneyTransferChoice1JaxbTranslator {

    /**
     * marshal P2PConfigChoice1ConfirmationRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(P2PConfigChoice1ConfirmationRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(P2PConfigChoice1ConfirmationRequest.class);

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
     * marshal P2PConfigChoice1ConfirmationResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(P2PConfigChoice1ConfirmationResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(P2PConfigChoice1ConfirmationResponse.class);

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
     * marshal P2PConfigChoice1InitiationRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(P2PConfigChoice1InitiationRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(P2PConfigChoice1InitiationRequest.class);

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
     * marshal P2PConfigChoice1InitiationRequest
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(P2PConfigChoice1InitiationRequest response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(P2PConfigChoice1InitiationRequest.class);

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
     * Unmarshal P2PConfigChoice1ConfirmationRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public P2PConfigChoice1ConfirmationRequest unmarshalStandardBillPaymentRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(P2PConfigChoice1ConfirmationRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        P2PConfigChoice1ConfirmationRequest request = (P2PConfigChoice1ConfirmationRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal StandartBillPaymentResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public P2PConfigChoice1ConfirmationResponse unmarshalP2PConfigChoice1ConfirmationResponsee(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(P2PConfigChoice1ConfirmationResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        P2PConfigChoice1ConfirmationResponse request = (P2PConfigChoice1ConfirmationResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


    /**
     * Unmarshal P2PConfigChoice1ConfirmationRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public P2PConfigChoice1InitiationRequest unmarshalP2PConfigChoice1InitiationRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(P2PConfigChoice1InitiationRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        P2PConfigChoice1InitiationRequest request = (P2PConfigChoice1InitiationRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal P2PConfigChoice1InitiationResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public P2PConfigChoice1InitiationResponse unmarshalP2PConfigChoice1InitiationResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(P2PConfigChoice1ConfirmationResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        P2PConfigChoice1InitiationResponse request = (P2PConfigChoice1InitiationResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

}
