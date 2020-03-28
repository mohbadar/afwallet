
package af.gov.anar.corona.notification.model;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "sms_gateway_configuration", schema = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
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

}
