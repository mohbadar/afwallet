package af.gov.anar.dck.instance.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "instance_assignee_history")
public class InstanceAssigneeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_assignee_history_generator")
	@SequenceGenerator(name="instance_assignee_history_generator", sequenceName = "instance_assignee_history_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(name = "env_slug")
	private String envSlug;
} 
	