package io.mosip.kernel.idgenerator.rid.exception;


/**
 * Exception class for empty inputs.
 *
 */

public class EmptyInputException extends BaseUncheckedException {
	private static final long serialVersionUID = 2842524563494167519L;

	public EmptyInputException() {
		super();

	}

	public EmptyInputException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

	public EmptyInputException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

}
