
package af.gov.anar.corona.notification.model;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "template", schema = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class TemplateEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "template_identifier")
	private String templateIdentifier;
	@Column(name = "subject")
	private String subject;
	@Column(name = "sender_email")
	private String senderEmail;
	@Column(name = "message")
	private String message;
	@Column(name = "url")
	private String url;

}
