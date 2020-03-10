package af.gov.anar.dck.systemregistry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import af.gov.anar.dck.form.model.Form;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

// @Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// @ToString
// @EqualsAndHashCode
@Entity(name = "SystemRegistry")
@Table(name = "system_registry")
public class SystemRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_registry_generator")
    @SequenceGenerator(name = "system_registry_generator", sequenceName = "system_registry_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    // map layer
    @Column(name = "registry_type")
    private String registryType;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "env_slug")
    private String envSlug;

    @ManyToMany(mappedBy = "mapLayers")
    @JsonIgnore
    private Collection<Form> forms;

    public SystemRegistry(String name, String description, String registryType) {
        this.name = name;
        this.description = description;
        this.registryType = registryType;
        this.active = false;
    }

    @Override
    public String toString() {
        return "Form [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
                + ", registry_type=" + registryType + ", envSlug=" + envSlug + ", created_at=" + createdAt
                + ", updated_at=" + updatedAt + "]";
    }
}
