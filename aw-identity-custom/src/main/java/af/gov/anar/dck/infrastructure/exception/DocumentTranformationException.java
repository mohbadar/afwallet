
package af.gov.anar.dck.infrastructure.exception;

import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;

public class DocumentTranformationException extends TransformerException {

    public DocumentTranformationException(String message) {
        super(message);
    }

    @Override
    public String getLocationAsString() {
        return super.getLocationAsString();
    }

    @Override
    public String getMessageAndLocation() {
        return super.getMessageAndLocation();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }

    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    @Override
    public Throwable getException() {
        return super.getException();
    }

    @Override
    public SourceLocator getLocator() {
        return super.getLocator();
    }

}
