
package af.asr.notification.model;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "template", schema = "notification")
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
	
	
	public TemplateEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTemplateIdentifier() {
		return templateIdentifier;
	}
	
	public void setTemplateIdentifier(String templateIdentifier) {
		this.templateIdentifier = templateIdentifier;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSenderEmail() {
		return senderEmail;
	}
	
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TemplateEntity that = (TemplateEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(templateIdentifier, that.templateIdentifier) &&
				Objects.equals(subject, that.subject) &&
				Objects.equals(url, that.url);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, templateIdentifier, subject, message, url);
	}
	
	@Override
	public String toString() {
		return "TemplateEntity{" +
				"id=" + id +
				", templateIdentifier='" + templateIdentifier + '\'' +
				", subject='" + subject + '\'' +
				", message='" + message + '\'' +
				", senderEmail='" + senderEmail + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
