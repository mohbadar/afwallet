package af.gov.anar.lib.velocitytemplate.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * this exception thrown when a resource of any type isn't found by the template
 * manager. <br>
 * When this exception is thrown, a best effort will be made to have useful
 * information in the exception's message. For complete information, consult the
 * runtime log.
 */
public class TemplateResourceNotFoundException extends BaseUncheckedException {

	private static final long serialVersionUID = 3070414901455295210L;

	/**
	 * Constructor for set error code and message
	 * 
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 */
	public TemplateResourceNotFoundException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	/**
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 * @param rootCause
	 *            cause of the error
	 */
	public TemplateResourceNotFoundException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);
	}

}
