package af.gov.anar.dck.instance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// @ToString(exclude="instance")
@EqualsAndHashCode
@Entity
@Table(name = "instance_watcher")
public class InstanceWatcher {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_watcher_generator")
	@SequenceGenerator(name="instance_watcher_generator", sequenceName = "instance_watcher_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	//userId 
    @Column(name = "watcher_id")
	private Long watcherId;

	@Column
	private String username;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "instance_id")
	@JsonIgnore
	private Instance instance;

	@Override
	public String toString() {
        return "Form [id=" + id + ", created_at=" + createdAt  + "]";
	}
}
