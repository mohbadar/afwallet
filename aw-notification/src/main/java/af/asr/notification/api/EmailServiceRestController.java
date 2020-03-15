
package af.asr.notification.api;

import af.asr.notification.ServiceConstants;
import af.asr.notification.domain.EmailConfiguration;
import af.asr.notification.service.EmailService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/configuration/email")
public class EmailServiceRestController {
	
	private final Logger logger;
	private EmailService emailService;
	
	@Autowired
	public EmailServiceRestController(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
	                                  final EmailService emailService) {
		super();
		this.logger = logger;
		this.emailService = emailService;
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
	List<EmailConfiguration> findAllEmailConfigurationEntities() {
		return this.emailService.findAllEmailConfigurationEntities();
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			value = "/{identifier}",
			method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<EmailConfiguration> findEmailConfigurationByIdentifier(@PathVariable("identifier") final String identifier) {
		return this.emailService.findEmailConfigurationByIdentifier(identifier)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> ServiceException.notFound("Email Gateway Configuration with identifier " + identifier + " doesn't exist."));
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> createEmailConfiguration(@RequestBody @Valid final EmailConfiguration emailConfiguration) throws InterruptedException {
		if (this.emailService.emailConfigurationExists(emailConfiguration.getIdentifier())) {
			throw ServiceException.conflict("Configuration {0} already exists.", emailConfiguration.getIdentifier());
		}
		
		this.emailService.createEmailConfiguration(emailConfiguration);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> updateEmailConfiguration(@RequestBody @Valid final EmailConfiguration emailConfiguration) {
		this.emailService.updateEmailConfiguration(emailConfiguration);
		return ResponseEntity.accepted().build();
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			value = "/{identifier}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> deleteEmailConfiguration(@PathVariable @Valid final String identifier) {
		this.emailService.deleteEmailConfiguration(identifier);
		return ResponseEntity.ok().build();
	}
}
