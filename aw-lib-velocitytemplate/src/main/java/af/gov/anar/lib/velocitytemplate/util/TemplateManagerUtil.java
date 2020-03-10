package af.gov.anar.lib.velocitytemplate.util;

import org.apache.velocity.VelocityContext;

import java.util.Map;

/**
 * TemplateManagerUtil contain Utility methods
 */
public class TemplateManagerUtil {

	private TemplateManagerUtil() {
	}

	/**
	 * Method to bind the map of values into VelocityContext
	 * 
	 * @param input as Map&lt;String,Object&gt; where key will be placeholder name
	 *              and Object is the actual value for the placeholder
	 * @return VelocityContext
	 */
	public static VelocityContext bindInputToContext(Map<String, Object> input) {
		VelocityContext context = null;
		if (input != null && !input.isEmpty()) {
			context = new VelocityContext(input);
		}

		return context;
	}
}
