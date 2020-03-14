
package af.gov.anar.hooks.hook.processor;

import static af.gov.anar.hooks.hook.api.HookApiConstants.smsTemplateName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.webTemplateName;

import af.gov.anar.hooks.hook.domain.Hook;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class HookProcessorProvider implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(
			final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public HookProcessor getProcessor(final Hook hook) {
		HookProcessor processor;
		final String templateName = hook.getHookTemplate().getName();
		if (templateName.equalsIgnoreCase(smsTemplateName)) {
			processor = this.applicationContext.getBean("twilioHookProcessor",
					TwilioHookProcessor.class);
		} else if (templateName.equals(webTemplateName)) {
			processor = this.applicationContext.getBean("webHookProcessor",
					WebHookProcessor.class);
		} else {
			processor = null;
		}
		return processor;
	}

}
