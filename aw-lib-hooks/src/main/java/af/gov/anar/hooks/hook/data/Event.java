
package af.gov.anar.hooks.hook.data;

import java.io.Serializable;

public class Event implements Serializable {

	private final String actionName;
	private final String entityName;

	public static Event instance(final String actionName,
			final String entityName) {
		return new Event(actionName, entityName);
	}

	private Event(final String actionName, final String entityName) {
		this.actionName = actionName;
		this.entityName = entityName;
	}

	public String getActionName() {
		return this.actionName;
	}

	public String getEntityName() {
		return this.entityName;
	}

}
