
package af.gov.anar.hooks.hook.domain;

import static af.gov.anar.hooks.hook.api.HookApiConstants.nameParamName;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import af.gov.anar.hooks.infrastructure.common.command.JsonCommand;
import af.gov.anar.lang.data.AbstractPersistableCustom;
import org.apache.commons.lang.StringUtils;


@Entity
@Table(name = "m_hook_templates")
public class HookTemplate extends AbstractPersistableCustom<Long> {

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "template", orphanRemoval = true, fetch=FetchType.EAGER)
	private Set<Schema> fields = new HashSet<>();

	private HookTemplate(final String name) {

		if (StringUtils.isNotBlank(name)) {
			this.name = name.trim();
		} else {
			this.name = null;
		}
	}

	protected HookTemplate() {

	}

	public static HookTemplate fromJson(final JsonCommand command) {
		final String name = command.stringValueOfParameterNamed(nameParamName);
		return new HookTemplate(name);
	}

	public String getName() {
		return this.name;
	}

	public Set<Schema> getSchema() {
		return this.fields;
	}
}
