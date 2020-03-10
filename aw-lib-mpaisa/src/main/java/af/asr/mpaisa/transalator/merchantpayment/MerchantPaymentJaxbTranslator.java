package af.asr.mpaisa.transalator.merchantpayment;

import af.asr.mpaisa.message.domestic_money_transfer.choice2.P2PConfigChoice2Request;
import af.asr.mpaisa.message.domestic_money_transfer.choice2.P2PConfigChoice2Response;
import af.asr.mpaisa.message.merchantpayment.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class MerchantPaymentJaxbTranslator {

    /**
     * marshal MerchantPaymentConfirmRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(MerchantPaymentConfirmRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchantPaymentConfirmRequest.class);

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
     * marshal MerchantPaymentConfirmResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(MerchantPaymentConfirmResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchantPaymentConfirmResponse.class);

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
     * marshal MerchantPaymentRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(MerchantPaymentRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchantPaymentRequest.class);

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
     * marshal MerchantPaymentResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(MerchantPaymentResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchantPaymentResponse.class);

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
     * marshal MerchantPaymentSingleStepRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(MerchantPaymentSingleStepRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchantPaymentSingleStepRequest.class);

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
     * marshal MerchantPaymentSingleStepResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(MerchantPaymentSingleStepResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchantPaymentSingleStepResponse.class);

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
     * Unmarshal MerchantPaymentConfirmRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public MerchantPaymentConfirmRequest unmarshalMerchantPaymentConfirmRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(MerchantPaymentConfirmRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        MerchantPaymentConfirmRequest request = (MerchantPaymentConfirmRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal MerchantPaymentConfirmResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public MerchantPaymentConfirmResponse unmarshalMerchantPaymentConfirmResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(P2PConfigChoice2Response.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        MerchantPaymentConfirmResponse request = (MerchantPaymentConfirmResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


    /**
     * Unmarshal MerchantPaymentRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public MerchantPaymentRequest unmarshalMerchantPaymentRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(MerchantPaymentRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        MerchantPaymentRequest request = (MerchantPaymentRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal MerchantPaymentResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public MerchantPaymentResponse unmarshalMerchantPaymentResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(MerchantPaymentResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        MerchantPaymentResponse request = (MerchantPaymentResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


    /**
     * Unmarshal MerchantPaymentSingleStepRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public MerchantPaymentSingleStepRequest unmarshalMerchantPaymentSingleStepRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(MerchantPaymentSingleStepRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        MerchantPaymentSingleStepRequest request = (MerchantPaymentSingleStepRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal MerchantPaymentSingleStepResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public MerchantPaymentSingleStepResponse unmarshalMerchantPaymentSingleStepResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(MerchantPaymentSingleStepResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        MerchantPaymentSingleStepResponse request = (MerchantPaymentSingleStepResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


}
