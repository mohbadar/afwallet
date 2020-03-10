
package af.gov.anar.hooks.hook.processor;


import af.gov.anar.hooks.hook.domain.Hook;

public interface HookProcessor {

	void process(Hook hook,  String payload, String entityName,
				 String actionName, String tenantIdentifier, String authToken);

}
