package af.asr.mpaisa.exception.mpaisa;

import af.asr.mpaisa.exception.common.BaseCheckedException;

import javax.xml.bind.JAXBException;

public class JaxbExceptioin extends JAXBException {

    public JaxbExceptioin(String message) {
        super(message);
    }

    public JaxbExceptioin(String message, String errorCode) {
        super(message, errorCode);
    }

    public JaxbExceptioin(Throwable exception) {
        super(exception);
    }

    public JaxbExceptioin(String message, Throwable exception) {
        super(message, exception);
    }

    public JaxbExceptioin(String message, String errorCode, Throwable exception) {
        super(message, errorCode, exception);
    }
}
