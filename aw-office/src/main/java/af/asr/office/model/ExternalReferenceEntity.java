
package af.asr.office.model;

import javax.persistence.*;

@Entity
@Table(name = "horus_external_references", schema = "office")
public class ExternalReferenceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "office_identifier", nullable = false, length = 32)
  private String officeIdentifier;
  @Column(name = "a_type", nullable = false, length = 32)
  private String type;
  @Column(name = "a_state", nullable = false, length = 256)
  private String state;

  public ExternalReferenceEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getOfficeIdentifier() {
    return this.officeIdentifier;
  }

  public void setOfficeIdentifier(final String officeIdentifier) {
    this.officeIdentifier = officeIdentifier;
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getState() {
    return this.state;
  }

  public void setState(final String state) {
    this.state = state;
  }
}
