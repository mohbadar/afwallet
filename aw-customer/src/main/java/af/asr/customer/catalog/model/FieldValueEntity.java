
package af.asr.customer.catalog.model;


import af.asr.customer.model.CustomerEntity;

import javax.persistence.*;

@Entity
@Table(name = "field_value",schema = "catalog")
public class FieldValueEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "entity_id")
  private CustomerEntity customer;
  @ManyToOne(optional = false)
  @JoinColumn(name = "field_id")
  private FieldEntity field;
  @Column(name = "a_value", length = 4096, nullable = false)
  private String value;

  public FieldValueEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public CustomerEntity getCustomer() {
    return this.customer;
  }

  public void setCustomer(final CustomerEntity customer) {
    this.customer = customer;
  }

  public FieldEntity getField() {
    return this.field;
  }

  public void setField(final FieldEntity field) {
    this.field = field;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(final String value) {
    this.value = value;
  }
}
