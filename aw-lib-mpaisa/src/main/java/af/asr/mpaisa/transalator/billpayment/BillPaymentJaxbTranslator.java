package af.asr.mpaisa.transalator.billpayment;

import af.asr.mpaisa.message.bankservice.WalletToBankRequest;
import af.asr.mpaisa.message.bankservice.WalletToBankResponse;
import af.asr.mpaisa.message.billpayment.*;
import af.asr.mpaisa.message.cashin.CashInRequest;
import af.asr.mpaisa.message.cashin.CashInResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class BillPaymentJaxbTranslator {

    /**
     * Marshal OneTimeBillPaymentRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(OneTimeBillPaymentRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(OneTimeBillPaymentRequest.class);

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
     * Marshal OnlineBillPaymentRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(OnlineBillPaymentRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(OnlineBillPaymentRequest.class);

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
     * Marshal StandardBillPaymentRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(StandardBillPaymentRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(StandardBillPaymentRequest.class);

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
     * Marshal OnlineOneTimeBillPaymentResponse
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalResponse(OneTimeBillPaymentResponse obj) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(OneTimeBillPaymentResponse.class);

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
//        System.out.println( xmlContent );

        return xmlContent;
    }



    /**
     * Marshal OnlineBillPaymentResponse
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalResponse(OnlineBillPaymentResponse obj) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(OnlineBillPaymentResponse.class);

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
//        System.out.println( xmlContent );

        return xmlContent;
    }


    /**
     * Marshal StandartBillPaymentResponse
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalResponse(StandartBillPaymentResponse obj) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(StandartBillPaymentResponse.class);

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
//        System.out.println( xmlContent );

        return xmlContent;
    }


    /**
     * Unmarshal OneTimeBillPaymentRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public OneTimeBillPaymentRequest unmarshalOneTimeBillPaymentRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(OneTimeBillPaymentRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        OneTimeBillPaymentRequest request = (OneTimeBillPaymentRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal OneTimeBillPaymentResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public OneTimeBillPaymentResponse unmarshalOneTimeBillPaymentResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(OneTimeBillPaymentResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        OneTimeBillPaymentResponse request = (OneTimeBillPaymentResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


    /**
     * Unmarshal OnlineBillPaymentRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public OnlineBillPaymentRequest unmarshalOnlineBillPaymentRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(OnlineBillPaymentRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        OnlineBillPaymentRequest request = (OnlineBillPaymentRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal OnlineBillPaymentResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public OnlineBillPaymentResponse unmarshalOnlineBillPaymentResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(OnlineBillPaymentResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        OnlineBillPaymentResponse request = (OnlineBillPaymentResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }


    /**
     * Unmarshal StandardBillPaymentRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public StandardBillPaymentRequest unmarshalStandardBillPaymentRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(StandardBillPaymentRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        StandardBillPaymentRequest request = (StandardBillPaymentRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal StandartBillPaymentResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public StandartBillPaymentResponse unmarshalStandartBillPaymentResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(OnlineBillPaymentResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        StandartBillPaymentResponse request = (StandartBillPaymentResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }



}
