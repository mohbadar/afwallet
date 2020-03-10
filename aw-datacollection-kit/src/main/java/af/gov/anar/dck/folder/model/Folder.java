package af.gov.anar.dck.folder.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
// @AllArgsConstructor
@Setter
@Getter

@Entity(name = "Folder")
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folder_generator")
    @SequenceGenerator(name = "folder_generator", sequenceName = "folder_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;

    @Column(name = "path")
    private String path;
    @Column(name = "env_slug")
    private String envSlug;
    @Column(name = "is_directory")
    private boolean isDirectory;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Folder(Long id, String name, String type, String path, String envSlug, boolean isDirectory) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.path = path;
        this.envSlug = envSlug;
        this.isDirectory = isDirectory;

    }

    public String toString() {
        return "Folder[id:" + this.id + ", name:" + this.name + ", path:" + this.path + "+ isDiectory: "
                + this.isDirectory + "]";
    }
}