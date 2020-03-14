
package af.gov.anar.hooks.hook.event;

import java.io.Serializable;

public class HookEventSource implements Serializable {

	private final String entityName;

	private final String actionName;

	public HookEventSource(final String entityName, final String actionName) {
		super();
		this.entityName = entityName;
		this.actionName = actionName;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public String getActionName() {
		return this.actionName;
	}

}
