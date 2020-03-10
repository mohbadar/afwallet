package af.gov.anar.lib.velocitytemplate.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * TemplateMethodInvocationException when reference method in template could not
 * be invoked.
 */
public class TemplateMethodInvocationException extends BaseUncheckedException {

	private static final long serialVersionUID = 6360842063626691912L;

	/**
	 * Constructor for set error code and message
	 * 
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 */
	public TemplateMethodInvocationException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	/**
	 * Constructor for setting error code, message and cause
	 * 
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 * @param rootCause
	 *            the specified cause
	 */
	public TemplateMethodInvocationException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);
	}

}
