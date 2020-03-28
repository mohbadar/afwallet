package io.mosip.kernel.idgenerator.rid.exception;



/**
 * Exception class for empty inputs.
 */

public class NullValueException extends BaseUncheckedException {
	private static final long serialVersionUID = 2842522178894167519L;

	public NullValueException() {
		super();

	}

	public NullValueException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

	public NullValueException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

}
