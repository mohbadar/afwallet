
package af.asr.office.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "office_offices", schema = "office")
public class OfficeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "parent_office_id")
  private Long parentOfficeId;
  @Column(name = "identifier")
  private String identifier;
  @Column(name = "a_name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "created_by")
  private String createdBy;
  @Column(name = "created_on")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn;
  @Column(name = "last_modified_by")
  private String lastModifiedBy;
  @Column(name = "last_modified_on")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedOn;

  public OfficeEntity() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentOfficeId() {
    return parentOfficeId;
  }

  public void setParentOfficeId(Long parentOfficeId) {
    this.parentOfficeId = parentOfficeId;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Date getLastModifiedOn() {
    return lastModifiedOn;
  }

  public void setLastModifiedOn(Date lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final OfficeEntity that = (OfficeEntity) o;

    return identifier.equals(that.identifier);

  }

  @Override
  public int hashCode() {
    return identifier.hashCode();
  }
}
