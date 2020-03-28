package af.asr.lib.healthid.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * rid exception
 */
public class RidException extends BaseUncheckedException {

	/**
	 * generated rid exception
	 */
	private static final long serialVersionUID = 4207046836454691003L;

	public RidException() {
		super();
	}

	public RidException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);
	}

	public RidException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public RidException(String errorMessage) {
		super(errorMessage);
	}

}
