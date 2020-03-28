package af.gov.anar.ebreshna.infrastructure.base;


import af.gov.anar.ebreshna.infrastructure.revision.AuditEnabledEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity extends AuditEnabledEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated ID")
    protected Long id;

    @Column(name = "version")
//    @Version
    @ApiModelProperty(notes = "Version of the row")
    private Long version;

    @Column(name = "created_at")
    @CreationTimestamp
    @ApiModelProperty(notes = "Created at attribute")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @ApiModelProperty(notes = "Updated ata attribute")
    private LocalDateTime updatedAt;

    @Column
    @ApiModelProperty(notes = "User who deleted the row")
    private String deletedBy;
    @ApiModelProperty(notes = "A flag that identifies if a row is deleted or not.")
    private boolean deleted;
    @ApiModelProperty(notes = "the date in which the record has been deleted")
    private Date deletedAt;
}
