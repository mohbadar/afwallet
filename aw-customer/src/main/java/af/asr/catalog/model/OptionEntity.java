
package af.asr.catalog.model;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nun_options")
public class OptionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "field_id")
  private FieldEntity field;
  @Column(name = "a_label", length = 256, nullable = false)
  private String label;
  @Column(name = "a_value", nullable = false)
  private Integer value;
  @Column(name = "created_by", length = 32)
  private String createdBy;
  @Column(name = "created_on")
  @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
  private LocalDateTime createdOn;

  public OptionEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public FieldEntity getField() {
    return this.field;
  }

  public void setField(final FieldEntity field) {
    this.field = field;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public Integer getValue() {
    return this.value;
  }

  public void setValue(final Integer value) {
    this.value = value;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(final String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(final LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }
}
