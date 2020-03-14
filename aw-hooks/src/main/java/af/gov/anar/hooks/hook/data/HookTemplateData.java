
package af.gov.anar.hooks.hook.data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class HookTemplateData implements Serializable {

	private final Long id;
	private final String name;

	// associations
	private final List<Field> schema;

	public static HookTemplateData instance(final Long id, final String name,
			final List<Field> schema) {
		return new HookTemplateData(id, name, schema);
	}

	private HookTemplateData(final Long id, final String name,
			final List<Field> schema) {
		this.id = id;
		this.name = name;

		// associations
		this.schema = schema;
	}

	public Long getServiceId() {
		return this.id;
	}

}
