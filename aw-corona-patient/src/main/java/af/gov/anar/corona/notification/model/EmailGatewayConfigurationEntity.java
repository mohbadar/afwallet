
package af.gov.anar.corona.notification.model;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "email_gateway_configuration", schema = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
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

}
