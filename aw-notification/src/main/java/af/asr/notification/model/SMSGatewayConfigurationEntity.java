
package af.asr.notification.model;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "sms_gateway_configuration", schema = "notification")
public class SMSGatewayConfigurationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "identifier")
	private String identifier;
	@Column(name = "account_sid")
	private String account_sid;
	@Column(name = "auth_token")
	private String auth_token;
	@Column(name = "sender_number")
	private String sender_number;
	@Column(name = "state")
	private String state;
	
	public SMSGatewayConfigurationEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}
	
	public String getAuth_token() {
		return auth_token;
	}
	
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	
	public String getAccount_sid() {
		return account_sid;
	}
	
	public void setAccount_sid(String account_sid) {
		this.account_sid = account_sid;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getSender_number() {
		return sender_number;
	}
	
	public void setSender_number(String sender_number) {
		this.sender_number = sender_number;
	}
}
