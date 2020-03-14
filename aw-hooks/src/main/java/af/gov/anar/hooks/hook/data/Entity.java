
package af.gov.anar.hooks.hook.data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class Entity implements Serializable {

	private String name;

	private List<String> actions;

	public void setName(final String name) {
		this.name = name;
	}

	public void setActions(final List<String> actions) {
		this.actions = actions;
	}

	public String getName() {
		return this.name;
	}

}
