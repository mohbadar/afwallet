
package af.asr.notification.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;


@Service
public class MailBuilder {
		private TemplateEngine templateEngine;
		
		@Autowired
		public MailBuilder(TemplateEngine templateEngine) {
			this.templateEngine = templateEngine;
		}
		
		public String build(Map<String, Object> message, String template) {
			Context context = new Context();
			for(Map.Entry m:message.entrySet()){
				context.setVariable(m.getKey().toString(), m.getValue().toString());
			}
			return templateEngine.process(template, context);
		}
}
