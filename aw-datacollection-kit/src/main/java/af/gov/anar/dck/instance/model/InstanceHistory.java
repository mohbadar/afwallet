package af.gov.anar.dck.instance.model;

import af.gov.anar.dck.infrastructure.util.enumeration.InstanceHistoryStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "instance_history")
public class InstanceHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_history_generator")
	@SequenceGenerator(name="instance_history_generator", sequenceName = "instance_history_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;
 
	@Column(name = "instance_id")
	private Long instanceId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column
	private String field;
	
	@Column(name = "new_value")
	private String newValue;
	
	@Column(name = "old_value")
	private String oldValue;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "instance_history_status")
	private InstanceHistoryStatus instanceHistoryStatus = InstanceHistoryStatus.UNVIEWED;
}
