package af.gov.anar.lib.velocitytemplate.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * TemplateConfigurationException if problem occurs while Configuring the
 * template Manager.

 */
public class TemplateConfigurationException extends BaseUncheckedException {

	private static final long serialVersionUID = -6167648722650250191L;

	/**
	 * Constructor for setting error code and message
	 * 
	 * @param errorCode    the error code
	 * @param errorMessage the error message
	 */
	public TemplateConfigurationException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
