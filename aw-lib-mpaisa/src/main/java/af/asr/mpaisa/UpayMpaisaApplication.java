package af.asr.mpaisa;

import af.asr.mpaisa.message.cashin.CashInRequest;
import af.asr.mpaisa.transalator.cashin.CashInJaxbTranslator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;

@SpringBootApplication
public class UpayMpaisaApplication {

	public static void main(String[] args) throws JAXBException {
		SpringApplication.run(UpayMpaisaApplication.class, args);

		CashInRequest cashInRequest = new CashInRequest();
		cashInRequest.setAMOUNT(new BigDecimal(1000));
		cashInRequest.setIDNO("9942223");
		cashInRequest.setLANGUAGE1(0);
		cashInRequest.setLANGUAGE1(0);
		cashInRequest.setMSISDN("9942222");
		cashInRequest.setMSISDN2("9942222");
		cashInRequest.setPIN(1234);
		cashInRequest.setTYPE("RCIREQ");
		cashInRequest.setSNDPROVIDER(101);
		cashInRequest.setSNDPROVIDER(12);
		cashInRequest.setRCVPROVIDER(101);
		cashInRequest.setRCVINSTRUMENT(12);

		CashInJaxbTranslator transalator = new CashInJaxbTranslator();
		transalator.marshalRequest(cashInRequest);

	}

}
