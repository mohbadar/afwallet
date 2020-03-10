package af.gov.anar.dck.useradministration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

// @Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// @ToString(exclude="permissions, groups")
// @EqualsAndHashCode
@Entity(name = "Role")
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
	@SequenceGenerator(name="role_generator", sequenceName = "role_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "active", length = 1, nullable = false)
    private boolean active;

	@Column(name = "env_slug")
	private String envSlug;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
    private Collection<Group> groups;

	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "role_permission", 
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Permission> permissions; 
    
    public Role(Long id,String name,String desc, Collection<Permission> permissions)
    {
    	this.id=id;
    	this.name=name;
    	this.description=desc;
    	this.permissions=permissions;
    }
    
	public Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
        return "Form [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active + ", envSlug=" + envSlug + "]";
	}
}
