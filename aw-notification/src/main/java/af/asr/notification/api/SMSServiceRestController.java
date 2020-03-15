
package af.asr.notification.api;

import af.asr.notification.ServiceConstants;
import af.asr.notification.domain.SMSConfiguration;
import af.asr.notification.service.SMSService;
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
@RequestMapping("/configuration/sms")
public class SMSServiceRestController {
	
	private final Logger logger;
	private final SMSService smsService;
	
	@Autowired
	public SMSServiceRestController(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
	                                final SMSService smsService) {
		super();
		this.logger = logger;
		this.smsService = smsService;
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
	List<SMSConfiguration> findAllSMSConfigurationEntities() {
		return this.smsService.findAllSMSConfigurationEntities();
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
    ResponseEntity<SMSConfiguration> findSMSConfigurationByIdentifier(@PathVariable("identifier") final String identifier) {
		return this.smsService.findSMSConfigurationByIdentifier(identifier)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> ServiceException.notFound("SMS Gateway Configuration with identifier " + identifier + " doesn't exist."));
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> createSMSConfiguration(@RequestBody @Valid final SMSConfiguration smsConfiguration) {
		if (this.smsService.smsConfigurationExists(smsConfiguration.getIdentifier())) {
			throw ServiceException.conflict("Configuration {0} already exists.", smsConfiguration.getIdentifier());
		}
		
		this.smsService.createSmsConfiguration(smsConfiguration);
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
    ResponseEntity<Void> updateSMSConfiguration(@RequestBody @Valid final SMSConfiguration smsConfiguration) {
		this.smsService.updateSmsConfiguration(smsConfiguration);
		return ResponseEntity.accepted().build();
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(value = "/{identifier}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> deleteSMSConfiguration(@PathVariable @Valid final String identifier) {
		this.smsService.deleteSmsConfiguration(identifier);
		return ResponseEntity.ok().build();
	}
}
