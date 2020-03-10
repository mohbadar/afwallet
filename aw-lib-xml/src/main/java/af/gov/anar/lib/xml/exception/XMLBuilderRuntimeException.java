package af.gov.anar.lib.xml.exception;

public class XMLBuilderRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -635323496745601589L;

    /**
     * @param exception
     * cause exception to be wrapped
     */
    public XMLBuilderRuntimeException(Exception exception) {
        super(exception);
    }

}