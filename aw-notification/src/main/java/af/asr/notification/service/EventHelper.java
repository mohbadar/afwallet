
package af.asr.notification.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@SuppressWarnings("WeakerAccess")
@Component
public class EventHelper {
//	private final Gson gson;
//	private final JmsTemplate jmsTemplate;
	
//	public EventHelper(final @Qualifier(CommandConstants.SERIALIZER) Gson gson, final JmsTemplate jmsTemplate) {
//		this.gson = gson;
//		this.jmsTemplate = jmsTemplate;
//	}
	
	public void sendEvent(final String eventName, final Object payload) {
//		this.jmsTemplate.convertAndSend(
//				this.gson.toJson(payload),
//				message -> {
//					message.setStringProperty(
//							TenantHeaderFilter.TENANT_HEADER,
//							tenantIdentifier);
//					message.setStringProperty(
//							NotificationEventConstants.SELECTOR_NAME,
//							eventName
//					);
//					return message;
//				}
//		);
	}
}
