

package af.gov.anar.corona.notification.mapper;


import af.gov.anar.corona.notification.domain.EmailConfiguration;
import af.gov.anar.corona.notification.model.EmailGatewayConfigurationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmailConfigurationMapper {
	
	private EmailConfigurationMapper() {
		super();
	}
	
	public static EmailConfiguration map(final EmailGatewayConfigurationEntity emailGatewayConfigurationEntity) {
		final EmailConfiguration emailConfiguration = new EmailConfiguration();
		emailConfiguration.setIdentifier(emailGatewayConfigurationEntity.getIdentifier());
		emailConfiguration.setHost(emailGatewayConfigurationEntity.getHost());
		emailConfiguration.setPort(emailGatewayConfigurationEntity.getPort());
		emailConfiguration.setUsername(emailGatewayConfigurationEntity.getUsername());
		emailConfiguration.setApp_password(emailGatewayConfigurationEntity.getApp_password());
		emailConfiguration.setProtocol(emailGatewayConfigurationEntity.getProtocol());
		emailConfiguration.setSmtp_auth(emailGatewayConfigurationEntity.getSmtp_auth());
		emailConfiguration.setStart_tls(emailGatewayConfigurationEntity.getStart_tls());
		emailConfiguration.setState(emailGatewayConfigurationEntity.getState());
		return emailConfiguration;
	}
	
	public static EmailGatewayConfigurationEntity map(final EmailConfiguration emailConfiguration) {
		final EmailGatewayConfigurationEntity emailGatewayConfigurationEntity = new EmailGatewayConfigurationEntity();
		emailGatewayConfigurationEntity.setIdentifier(emailConfiguration.getIdentifier());
		emailGatewayConfigurationEntity.setHost(emailConfiguration.getHost());
		emailGatewayConfigurationEntity.setPort(emailConfiguration.getPort());
		emailGatewayConfigurationEntity.setProtocol(emailConfiguration.getProtocol());
		emailGatewayConfigurationEntity.setApp_password(emailConfiguration.getApp_password());
		emailGatewayConfigurationEntity.setUsername(emailConfiguration.getUsername());
		emailGatewayConfigurationEntity.setSmtp_auth(emailConfiguration.getSmtp_auth());
		emailGatewayConfigurationEntity.setStart_tls(emailConfiguration.getStart_tls());
		emailGatewayConfigurationEntity.setState(emailConfiguration.getState());
		return emailGatewayConfigurationEntity;
	}
	
	public static List<EmailConfiguration> map(final List<EmailGatewayConfigurationEntity> emailGatewayConfigurationEntity) {
		final ArrayList<EmailConfiguration> emailConfigurationList = new ArrayList<>(emailGatewayConfigurationEntity.size());
		emailConfigurationList.addAll(emailGatewayConfigurationEntity.stream().map(EmailConfigurationMapper::map).collect(Collectors.toList()));
		return emailConfigurationList;
	}
}

