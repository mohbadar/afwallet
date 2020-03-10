package af.gov.anar.dck.instance.model;

import af.gov.anar.dck.useradministration.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
// @ToString(exclude="user, instance")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instance_transition")
public class InstanceTransition {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_transition_generator")
	@SequenceGenerator(name="instance_transition_generator", sequenceName = "instance_transition_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Instance.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "instance_id")
    private Instance instance;

    @Column(name = "previous_step", insertable = true, updatable = false)
    private String previousStep;

    @Column(name = "next_step", insertable = true, updatable = false)
    private String nextStep;

    @Column(name = "resolution", insertable = true, updatable = false)
    @Type(type = "text")
    private String resolution;

    @Column(name = "remarks", insertable = true, updatable = false)
    private String remarks;

    @Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;


    public InstanceTransition(User user, Instance instance, String previousStep, String nextStep, String resolution, String remarks)
    {
        this.user= user;
        this.instance=instance;
        this.previousStep=previousStep;
        this.nextStep=nextStep;
        this.resolution=resolution;
        this.remarks = remarks;
    }

    public InstanceTransition(Long id, User user, String previousStep, String nextStep, String resolution, String remarks, LocalDateTime createdAt, LocalDateTime updatedAt)
    {
        this.id = id;
        this.user= user;
        this.previousStep=previousStep;
        this.nextStep=nextStep;
        this.resolution=resolution;
        this.remarks = remarks;
        this.createdAt = createdAt;
		this.updatedAt = updatedAt;
    }

    @Override
	public String toString() {
        return "Form [id=" + id + ", previousStep=" + previousStep + ", resolution=" + resolution + ", remarks=" + remarks +", nextStep=" + nextStep + "]";
	}
}


