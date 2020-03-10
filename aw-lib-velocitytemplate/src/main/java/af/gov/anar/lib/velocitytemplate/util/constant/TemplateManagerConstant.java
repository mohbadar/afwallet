package af.gov.anar.lib.velocitytemplate.util.constant;

/**
 * constants for NullPointerException Messages
 */
public enum TemplateManagerConstant {
	WRITER_NULL("Writer cannot be null"), TEMPLATE_VALUES_NULL("Values cannot be null, it requires process template"),
	TEMPLATE_INPUT_STREAM_NULL("Template cannot be null"), ENCODING_TYPE_NULL("Encoding type cannot be null"),
	TEMPATE_NAME_NULL("Template name cannot be null");

	/**
	 * This variable contains the message
	 */
	private String message;

	/**
	 * Constructor for setting message
	 * 
	 * @param message
	 */
	TemplateManagerConstant(String message) {
		this.message = message;
	}

	/**
	 * Getter for getting the message
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
}
