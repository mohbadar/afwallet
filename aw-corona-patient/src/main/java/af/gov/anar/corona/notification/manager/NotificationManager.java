
package af.gov.anar.corona.notification.manager;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.util.CustomFeignClientsConfiguration;
import af.gov.anar.corona.notification.domain.EmailConfiguration;
import af.gov.anar.corona.notification.domain.SMSConfiguration;
import af.gov.anar.corona.notification.domain.Template;
import af.gov.anar.corona.notification.exception.ConfigurationAlreadyExistException;
import af.gov.anar.corona.notification.exception.TemplateAlreadyExistException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@SuppressWarnings("unused")
@FeignClient(value = "notification-v1", path = "/notification/v1", configuration = CustomFeignClientsConfiguration.class)
public interface NotificationManager {

	@RequestMapping(
			value = "/configuration/sms",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	List<SMSConfiguration> findAllSMSConfigurations();

	@RequestMapping(
			value = "/configuration/email",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	List<EmailConfiguration> findAllEmailConfigurations();

	@RequestMapping(
			value = "/configuration/sms",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ThrowsExceptions({
			@ThrowsException(status = HttpStatus.CONFLICT, exception = ConfigurationAlreadyExistException.class)
	})
	String createSMSConfiguration(final SMSConfiguration smsConfiguration);

	@RequestMapping(
			value = "/configuration/email",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ThrowsExceptions({
			@ThrowsException(status = HttpStatus.CONFLICT, exception = ConfigurationAlreadyExistException.class)
	})
	String createEmailConfiguration(final EmailConfiguration emailConfiguration);

	@RequestMapping(
			value = "/template",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ThrowsExceptions({
			@ThrowsException(status = HttpStatus.CONFLICT, exception = TemplateAlreadyExistException.class)
	})
	String createTemplate(final Template template);

	@RequestMapping(
			value = "/configuration/sms/{identifier}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	SMSConfiguration findSMSConfigurationByIdentifier(@PathVariable("identifier") final String identifier);

	@RequestMapping(
			value = "/configuration/email/{identifier}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	EmailConfiguration findEmailConfigurationByIdentifier(@PathVariable("identifier") final String identifier);

	@RequestMapping(value = "/configuration/sms",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	void updateSMSConfiguration(final SMSConfiguration smsConfiguration);

	@RequestMapping(value = "/configuration/email",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	void updateEmailConfiguration(final EmailConfiguration emailConfiguration);

	@RequestMapping(value = "/configuration/sms/{identifier}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	void deleteSMSConfiguration(@PathVariable("identifier") final String identifier);

	@RequestMapping(value = "/configuration/email/{identifier}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	void deleteEmailConfiguration(@PathVariable("identifier") final String identifier);
}
