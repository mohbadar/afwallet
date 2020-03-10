
package af.gov.anar.hooks.hook.event;

import org.springframework.context.ApplicationEvent;

public class HookEvent extends ApplicationEvent {

	private final String payload;

	private final String tenantIdentifier;

//	private final AppUser appUser;

	private final String authToken;

	public HookEvent(final HookEventSource source, final String payload,
			final String tenantIdentifier,
			final String authToken) {
		super(source);
		this.payload = payload;
		this.tenantIdentifier = tenantIdentifier;
		this.authToken = authToken;
	}

	public String getPayload() {
		return this.payload;
	}

	@Override
	public HookEventSource getSource() {
		return (HookEventSource) super.source;
	}

	public String getTenantIdentifier() {
		return this.tenantIdentifier;
	}

//	public AppUser getAppUser() {
//		return this.appUser;
//	}

	public String getAuthToken() {
		return this.authToken;
	}

}
