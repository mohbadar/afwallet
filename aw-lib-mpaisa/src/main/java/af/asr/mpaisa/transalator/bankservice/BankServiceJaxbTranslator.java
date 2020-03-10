package af.asr.mpaisa.transalator.bankservice;

import af.asr.mpaisa.message.bankservice.BankToWalletRequest;
import af.asr.mpaisa.message.bankservice.BankToWalletResponse;
import af.asr.mpaisa.message.bankservice.WalletToBankRequest;
import af.asr.mpaisa.message.bankservice.WalletToBankResponse;
import af.asr.mpaisa.message.cashin.CashInRequest;
import af.asr.mpaisa.message.cashin.CashInResponse;
import af.asr.mpaisa.message.cashout.CashOutRequest;
import af.asr.mpaisa.message.cashout.CashoutResponse;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class BankServiceJaxbTranslator {

    /**
     * marshal BankToWalletRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(BankToWalletRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(BankToWalletRequest.class);

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

    /**
     * marshal BankToWalletResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(BankToWalletResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(BankToWalletResponse.class);

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
        System.out.println( xmlContent );

        return xmlContent;
    }



    /**
     * marshal WalletToBankRequest
     * @param obj
     * @return
     * @throws JAXBException
     */
    public String marshalRequest(WalletToBankRequest obj) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(WalletToBankRequest.class);

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
     * marshal WalletToBankResponse
     * @param response
     * @return
     * @throws JAXBException
     */
    public String marshaResponse(WalletToBankResponse response) throws JAXBException {

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(WalletToBankResponse.class);

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
        System.out.println( xmlContent );

        return xmlContent;
    }


    /**
     * Unmarshal BankToWalletRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public BankToWalletRequest unmarshalBankToWalletRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(BankToWalletRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        BankToWalletRequest request = (BankToWalletRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal BankToWalletResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public BankToWalletResponse unmarshalBankToWalletResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(BankToWalletResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        BankToWalletResponse request = (BankToWalletResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal WalletToBankRequest
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public WalletToBankRequest unmarshalWalletToBankRequest(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(WalletToBankRequest.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        WalletToBankRequest request = (WalletToBankRequest) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }

    /**
     * Unmarshal BankToWalletResponse
     * @param xmlContent
     * @return
     * @throws JAXBException
     */
    public WalletToBankResponse unmarshalWalletToBankResponse(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext  = JAXBContext.newInstance(WalletToBankResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        WalletToBankResponse request = (WalletToBankResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlContent));
        return request;
    }



}
