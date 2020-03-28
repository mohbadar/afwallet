package af.asr.healthid.constant;

/**
 * This enum provide all constants for rid generator.
 */
public enum RidGeneratorPropertyConstant {
	TIMESTAMP_FORMAT("yyyyMMddHHmmss");

	/**
	 * The property for ridgenerator.
	 */
	private String property;

	/**
	 * The constructor to set rid property.
	 * 
	 * @param property the property to set.
	 */
	private RidGeneratorPropertyConstant(String property) {
		this.property = property;
	}

	/**
	 * Getter for rid property
	 * 
	 * @return the rid property.
	 */
	public String getProperty() {
		return property;
	}

}
