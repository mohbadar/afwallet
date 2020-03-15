
package af.asr.notification.service;


import af.asr.notification.ServiceConstants;
import af.asr.notification.domain.SMSConfiguration;
import af.asr.notification.mapper.SMSConfigurationMapper;
import af.asr.notification.model.SMSGatewayConfigurationEntity;
import af.asr.notification.repository.SMSGatewayConfigurationRepository;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SMSService {
	
	static boolean isConfigured;
	private final SMSGatewayConfigurationRepository smsGatewayConfigurationRepository;
	private final Logger logger;
	private String accountSid;
	private String authToken;
	private String senderNumber;
	
	@Autowired
	public SMSService(final SMSGatewayConfigurationRepository smsGatewayConfigurationRepository,
	                  @Qualifier(ServiceConstants.LOGGER_NAME) Logger logger) {
		super();
		this.logger = logger;
		this.isConfigured = false;
		this.smsGatewayConfigurationRepository = smsGatewayConfigurationRepository;
	}
	
	//@PostConstruct
	public void init() {
		if (getDefaultSMSConfiguration().isPresent()){
			configureServiceWithDefaultGateway();
		}else{
			//Todo: Send an alert on the interface to configure the service
		}
	}
	
	public boolean configureServiceWithDefaultGateway() {
		SMSConfiguration configuration = getDefaultSMSConfiguration().get();
		this.accountSid = configuration.getAccount_sid();
		this.authToken = configuration.getAuth_token();
		this.senderNumber = configuration.getSender_number();
		return this.isConfigured = true;
	}
	
	public boolean customConfiguration(String identifier) {
		SMSConfiguration configuration = findSMSConfigurationByIdentifier(identifier).get();
		this.accountSid = configuration.getAccount_sid();
		this.authToken = configuration.getAuth_token();
		this.senderNumber = configuration.getSender_number();
		return this.isConfigured = true;
	}
	
	public Optional<SMSConfiguration> getDefaultSMSConfiguration() {
		return this.smsGatewayConfigurationRepository.defaultGateway().map(SMSConfigurationMapper::map);
	}
	
	public Boolean smsConfigurationExists(final String identifier) {
		return this.smsGatewayConfigurationRepository.existsByIdentifier(identifier);
	}
	
	public Optional<SMSConfiguration> findSMSConfigurationByIdentifier(final String identifier) {
		return this.smsGatewayConfigurationRepository.findByIdentifier(identifier).map(SMSConfigurationMapper::map);
	}
	
	public List<SMSConfiguration> findAllSMSConfigurationEntities() {
		return SMSConfigurationMapper.map(this.smsGatewayConfigurationRepository.findAll());
	}
	@Transactional
	public int sendSMS(String receiver, String template) {
		Twilio.init(this.accountSid, this.authToken);
		MessageCreator messageCreator = Message.creator(this.accountSid,
				new PhoneNumber(receiver),
				new PhoneNumber(this.senderNumber),
				template);
		Message message = null;
		try{
			message = messageCreator.create();

		}catch (ApiException apiException){
			logger.error("Error: {}" ,apiException.getMoreInfo());
		}

		return message.hashCode();
	}


	@Transactional
	public String createSmsConfiguration(final SMSConfiguration smsConfiguration) {
		final SMSGatewayConfigurationEntity entity = SMSConfigurationMapper.map(smsConfiguration);
		this.smsGatewayConfigurationRepository.save(entity);

		return smsConfiguration.getIdentifier();
	}

	@Transactional
	public String updateSmsConfiguration(final SMSConfiguration smsConfiguration) {
		final SMSGatewayConfigurationEntity newEntity = SMSConfigurationMapper.map(smsConfiguration);
		this.smsGatewayConfigurationRepository.deleteSMSGatewayConfigurationEntityByIdentifier(newEntity.getIdentifier());
		this.smsGatewayConfigurationRepository.save(newEntity);

		return newEntity.getIdentifier();
	}

	@Transactional
	public String deleteSmsConfiguration(final String identifier) {
		smsGatewayConfigurationRepository.deleteSMSGatewayConfigurationEntityByIdentifier(identifier);
		return identifier;
	}
}
