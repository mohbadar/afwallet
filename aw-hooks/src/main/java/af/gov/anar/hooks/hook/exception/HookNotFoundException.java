
package af.gov.anar.hooks.hook.exception;


import af.gov.anar.lang.infrastructure.exception.common.AbstractPlatformResourceNotFoundException;

public class HookNotFoundException extends
		AbstractPlatformResourceNotFoundException {

	public HookNotFoundException(final String name) {
		super("error.msg.hook.not.found", "Hook with name `" + name
				+ "` does not exist", name);
	}

	public HookNotFoundException(final Long hookId) {
		super("error.msg.hook.identifier.not.found", "Hook with identifier `"
				+ hookId + "` does not exist", hookId);
	}
}
