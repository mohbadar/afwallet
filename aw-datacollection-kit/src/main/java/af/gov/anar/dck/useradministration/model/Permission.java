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
// @ToString(exclude="roles")
// @EqualsAndHashCode
@Entity(name = "Permission")
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_generator")
    @SequenceGenerator(name = "permission_generator", sequenceName = "permission_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active", length = 1, nullable = false)
    private boolean active;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Collection<Role> roles;

    public Permission(String name) {
        this.name = name;
    }
    public Permission(long id, String name)
    {
        this.id = id;
        this.name= name;
    }

    public Permission(Long id, String name, String description, boolean active) {

    }

    @Override
	public String toString() {
        return "Form [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active + "]";
	}
}
