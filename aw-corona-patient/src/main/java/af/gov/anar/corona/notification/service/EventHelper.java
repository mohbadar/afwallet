
package af.gov.anar.corona.notification.service;

import af.gov.anar.lib.kafka.producer.AnarKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("WeakerAccess")
@Component
public class EventHelper {

	@Autowired
	private AnarKafkaProducer<Object> anarKafkaProducer;
//	private final Gson gson;
//	private final JmsTemplate jmsTemplate;

//	public EventHelper(final @Qualifier(CommandConstants.SERIALIZER) Gson gson, final JmsTemplate jmsTemplate) {
//		this.gson = gson;
//		this.jmsTemplate = jmsTemplate;
//	}

	public void sendEvent(final String eventName, final Object payload) {
		this.anarKafkaProducer.produce(eventName, payload);
	}
}
