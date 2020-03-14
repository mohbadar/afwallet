
package af.gov.anar.hooks.hook.listener;

import af.gov.anar.hooks.hook.domain.Hook;
import af.gov.anar.hooks.hook.event.HookEvent;
import af.gov.anar.hooks.hook.event.HookEventSource;
import af.gov.anar.hooks.hook.processor.HookProcessor;
import af.gov.anar.hooks.hook.processor.HookProcessorProvider;
import af.gov.anar.hooks.hook.service.HookReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineractHookListener implements HookListener {

    private final HookProcessorProvider hookProcessorProvider;
    private final HookReadPlatformService hookReadPlatformService;

    @Autowired
    public FineractHookListener(final HookProcessorProvider hookProcessorProvider,
            final HookReadPlatformService hookReadPlatformService) {
        this.hookReadPlatformService = hookReadPlatformService;
        this.hookProcessorProvider = hookProcessorProvider;
    }

    @Override
    public void onApplicationEvent(final HookEvent event) {

        final String tenantIdentifier = event.getTenantIdentifier();
        final String authToken = event.getAuthToken();

        final HookEventSource hookEventSource = event.getSource();
        final String entityName = hookEventSource.getEntityName();
        final String actionName = hookEventSource.getActionName();
        final String payload = event.getPayload();

        final List<Hook> hooks = this.hookReadPlatformService
                .retrieveHooksByEvent(hookEventSource.getEntityName(),
                        hookEventSource.getActionName());

        for (final Hook hook : hooks) {
            final HookProcessor processor = this.hookProcessorProvider
                    .getProcessor(hook);
            processor.process(hook, payload, entityName, actionName,
                    tenantIdentifier, authToken);
        }
    }

}
