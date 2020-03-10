package af.gov.anar.dck.instance.model;

import af.gov.anar.dck.useradministration.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Setter
@Getter
// @ToString(exclude="user, instance")
@EqualsAndHashCode
@Entity(name = "InstanceAttachment")
@Table(name = "instance_attachment")
public class InstanceAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_attachment_generator")
	@SequenceGenerator(name="instance_attachment_generator", sequenceName = "instance_attachment_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "attachment")
	private String attachment;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name = "env_slug")
	private String envSlug;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "instance_id")
	@JsonIgnore
	private Instance instance;
	
	public InstanceAttachment() { }

	@Override
	public String toString() {
        return "Form [id=" + id + ", attachment=" + attachment + ", envSlug=" + envSlug + ", created_at=" + createdAt  + ", updated_at=" + updatedAt + "]";
	}
}