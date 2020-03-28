
package af.gov.anar.corona.notification.domain;
/*
ebenezergraham created on 8/19/18
*/

import java.util.Objects;

public class Event {
	private final String identifier;
	private boolean enabled;
	
	public Event(String identifier, boolean enabled) {
		super();
		this.identifier = identifier;
		this.enabled = enabled;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean state) {
		this.enabled = state;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return enabled == event.enabled &&
				Objects.equals(identifier, event.identifier);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(identifier, enabled);
	}
	
	@Override
	public String toString() {
		return "Event{" +
				"identifier='" + identifier + '\'' +
				", enabled=" + enabled +
				'}';
	}
}
