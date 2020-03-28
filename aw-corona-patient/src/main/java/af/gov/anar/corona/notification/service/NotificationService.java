
package af.gov.anar.corona.notification.service;


import af.gov.anar.corona.notification.ServiceConstants;
import af.gov.anar.corona.notification.domain.Template;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {

	private final SMSService smsService;
	private final EmailService emailService;
	private final TemplateService templateService;

//	private final ExternalCustomerService customerService;
	private final Logger logger;

	@Autowired

	public NotificationService(final SMSService smsService,
	                           final EmailService emailService,
	                           final TemplateService templateService,
	                           @Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger
	) {
		super();
		this.smsService = smsService;
		this.emailService = emailService;
		this.templateService = templateService;
		this.logger = logger;
	}

//	public Optional<Customer> findCustomer(final String customerIdentifier) {
//		return customerService.findCustomer(customerIdentifier);
//	}

	public int sendSMS(String receiver, String template) {
		if (!this.smsService.isConfigured) this.smsService.configureServiceWithDefaultGateway();
		return this.smsService.sendSMS(receiver, template);
	}

	/*To be used as a backup should Formatted email fail*/
	public void sendEmail(String to, String templateIdentifier,Object payload) {
		Template template = this.templateService.findTemplateWithIdentifier(templateIdentifier).get();
		if (!this.emailService.isConfigured) {
			this.emailService.setNewConfiguration(template.getSenderEmail());
		}
		this.emailService.sendPlainEmail(to, template.getSubject(), template.getMessage());
	}

	public void sendFormattedEmail(String to, String templateIdentifier, Map<String,Object> variables) {
		Template template = this.templateService.findTemplateWithIdentifier(templateIdentifier).get();
		if (!this.emailService.isConfigured) {
			this.emailService.setNewConfiguration(template.getSenderEmail());
		}

		this.emailService.sendFormattedEmail(to, template.getSubject(), variables,template.getUrl());
	}
}
