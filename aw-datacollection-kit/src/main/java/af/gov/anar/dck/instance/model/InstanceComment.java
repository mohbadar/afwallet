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
@NoArgsConstructor
@Setter
@Getter
// @ToString(exclude="user, instance")
@EqualsAndHashCode
@Entity(name = "InstanceComment")
@Table(name = "instance_comment")
public class InstanceComment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_comment_generator")
	@SequenceGenerator(name="instance_comment_generator", sequenceName = "instance_comment_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "instance_id")
	@JsonIgnore
	private Instance instance;

	@Override
	public String toString() {
        return "Form [id=" + id + ", comment=" + comment + ", created_at=" + createdAt  + ", updated_at=" + updatedAt + "]";
	}
}