package af.asr.lib.healthid.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * Exception class for inputs lengths.
 *
 */

public class InputLengthException extends BaseUncheckedException {
	private static final long serialVersionUID = 2842522173497867519L;

	public InputLengthException() {
		super();

	}

	public InputLengthException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

	public InputLengthException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

}
