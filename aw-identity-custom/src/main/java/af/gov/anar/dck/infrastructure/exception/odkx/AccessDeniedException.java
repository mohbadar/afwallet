package af.gov.anar.dck.common.exception.odkx;

import java.io.Serializable;

/**
 * Access denied exception that can be returned through GWT.
 * 
 * @author mitchellsundt@gmail.com
 *
 */
public class AccessDeniedException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3102327639058143399L;
	
	private String message;

	/**
	 * 
	 */
	public AccessDeniedException() {
		super();
		message = "AccessDeniedException";
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AccessDeniedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		message = arg0 + "(" + arg1.getMessage() + ")";
	}

	/**
	 * @param arg0
	 */
	public AccessDeniedException(String arg0) {
		super(arg0);
		message = arg0;
	}

	/**
	 * @param arg0
	 */
	public AccessDeniedException(Throwable arg0) {
		super(arg0);
		message = "AccessDeniedException (" + arg0.getMessage() + ")";
	}

	@Override
	public String getLocalizedMessage() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
