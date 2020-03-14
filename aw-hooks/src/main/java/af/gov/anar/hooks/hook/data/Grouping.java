
package af.gov.anar.hooks.hook.data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class Grouping implements Serializable {

	private String name;

	private List<Entity> entities;

	public void setName(final String name) {
		this.name = name;
	}

	public void setEntities(final List<Entity> entities) {
		this.entities = entities;
	}

	public String getName() {
		return this.name;
	}

}
