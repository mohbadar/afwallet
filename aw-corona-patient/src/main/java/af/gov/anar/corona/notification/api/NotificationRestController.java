
package af.gov.anar.corona.notification.api;

import af.gov.anar.corona.notification.ServiceConstants;
import af.gov.anar.corona.notification.service.NotificationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/")
public class NotificationRestController {
	
	private final Logger logger;
	private final NotificationService notificationService;
	
	@Autowired
	public NotificationRestController(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
	                                  final NotificationService notificationService) {
		super();
		this.logger = logger;
		this.notificationService = notificationService;
	}
	
	////@Permittable(value = AcceptedTokenType.SYSTEM)
	@RequestMapping(
			value = "/initialize",
			method = RequestMethod.POST,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> initialize() throws InterruptedException {
//		this.commandGateway.process(new InitializeServiceCommand());
		return ResponseEntity.accepted().build();
	}
	
}
