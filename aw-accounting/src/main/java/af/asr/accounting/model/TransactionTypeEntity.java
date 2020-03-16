
package af.asr.accounting.model;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "acc_tx_types", schema = "accounting")
public class TransactionTypeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "identifier", nullable = false, length = 32)
  private String identifier;
  @Column(name = "a_name", nullable = false, length = 256)
  private String name;
  @SuppressWarnings("DefaultAnnotationParam")
  @Column(name = "description", nullable = true, length = 2048)
  private String description;

  public TransactionTypeEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }
}
