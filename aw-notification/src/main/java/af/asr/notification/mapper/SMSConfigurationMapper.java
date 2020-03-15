
package af.asr.notification.mapper;

import af.asr.notification.domain.SMSConfiguration;
import af.asr.notification.model.SMSGatewayConfigurationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SMSConfigurationMapper {
	
	private SMSConfigurationMapper() {
		super();
	}
	
	public static SMSConfiguration map(final SMSGatewayConfigurationEntity smsGatewayConfigurationEntity) {
		final SMSConfiguration smsConfiguration = new SMSConfiguration();
		smsConfiguration.setIdentifier(smsGatewayConfigurationEntity.getIdentifier());
		smsConfiguration.setAccount_sid(smsGatewayConfigurationEntity.getAccount_sid());
		smsConfiguration.setAuth_token(smsGatewayConfigurationEntity.getAuth_token());
		smsConfiguration.setSender_number(smsGatewayConfigurationEntity.getSender_number());
		smsConfiguration.setState(smsGatewayConfigurationEntity.getState());
		return smsConfiguration;
	}
	
	public static SMSGatewayConfigurationEntity map(final SMSConfiguration smsConfiguration) {
		final SMSGatewayConfigurationEntity smsGatewayConfigurationEntity = new SMSGatewayConfigurationEntity();
		smsGatewayConfigurationEntity.setIdentifier(smsConfiguration.getIdentifier());
		smsGatewayConfigurationEntity.setAccount_sid(smsConfiguration.getAccount_sid());
		smsGatewayConfigurationEntity.setAuth_token(smsConfiguration.getAuth_token());
		smsGatewayConfigurationEntity.setSender_number(smsConfiguration.getSender_number());
		smsGatewayConfigurationEntity.setState(smsConfiguration.getState());
		return smsGatewayConfigurationEntity;
	}
	
	public static List<SMSConfiguration> map(final List<SMSGatewayConfigurationEntity> smsGatewayConfigurationEntity) {
		final ArrayList<SMSConfiguration> smsConfigurationList = new ArrayList<>(smsGatewayConfigurationEntity.size());
		smsConfigurationList.addAll(smsGatewayConfigurationEntity.stream().map(SMSConfigurationMapper::map).collect(Collectors.toList()));
		return smsConfigurationList;
	}
}
