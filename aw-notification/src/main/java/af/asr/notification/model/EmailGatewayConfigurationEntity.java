
package af.asr.notification.model;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "email_gateway_configuration", schema = "notification")
public class EmailGatewayConfigurationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "identifier")
	private String identifier;
	@Column(name = "host")
	private String host;
	@Column(name = "port")
	private String port;
	@Column(name = "username")
	private String username;
	@Column(name = "app_password")
	private String app_password;
	@Column(name = "protocol")
	private String protocol;
	@Column(name = "smtp_auth")
	private String smtp_auth;
	@Column(name = "start_tls")
	private String start_tls;
	@Column(name = "state")
	private String state;
	
	public EmailGatewayConfigurationEntity() {
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
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getProtocol() {
		return protocol;
	}
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getApp_password() {
		return app_password;
	}
	
	public void setApp_password(String app_password) {
		this.app_password = app_password;
	}
	
	public String getSmtp_auth() {
		return smtp_auth;
	}
	
	public void setSmtp_auth(String smtp_auth) {
		this.smtp_auth = smtp_auth;
	}
	
	public String getStart_tls() {
		return start_tls;
	}
	
	public void setStart_tls(String start_tls) {
		this.start_tls = start_tls;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}
