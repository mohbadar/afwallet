
package af.asr.catalog.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "field")
public class FieldEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "catalog_id")
  private CatalogEntity catalog;
  @Column(name = "identifier", length = 32, nullable = false)
  private String identifier;
  @Column(name = "data_type", length = 256, nullable = false)
  private String dataType;
  @Column(name = "a_label", length = 256, nullable = false)
  private String label;
  @Column(name = "a_hint", length = 512)
  private String hint;
  @Length(max = 4096)
//  @JoinColumn(name = "description")
  @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<OptionEntity> options;
  private String description;
  @Column(name = "mandatory")
  private Boolean mandatory;
  @Column(name = "a_length")
  private Integer length;
  @Column(name = "a_precision")
  private Integer precision;
  @Column(name = "min_value")
  private Double minValue;
  @Column(name = "max_value")
  private Double maxValue;
  @Column(name = "created_by")
  private String createdBy;
  @Column(name = "created_on")
//  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime createdOn;

  public FieldEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public CatalogEntity getCatalog() {
    return this.catalog;
  }

  public void setCatalog(final CatalogEntity catalog) {
    this.catalog = catalog;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  public String getDataType() {
    return this.dataType;
  }

  public void setDataType(final String dataType) {
    this.dataType = dataType;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public String getHint() {
    return this.hint;
  }

  public void setHint(final String hint) {
    this.hint = hint;
  }

  public List<OptionEntity> getOptions() {
    return this.options;
  }

  public void setOptions(final List<OptionEntity> options) {
    this.options = options;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public Boolean getMandatory() {
    return this.mandatory;
  }

  public void setMandatory(final Boolean mandatory) {
    this.mandatory = mandatory;
  }

  public Integer getLength() {
    return this.length;
  }

  public void setLength(final Integer length) {
    this.length = length;
  }

  public Integer getPrecision() {
    return this.precision;
  }

  public void setPrecision(final Integer precision) {
    this.precision = precision;
  }

  public Double getMinValue() {
    return this.minValue;
  }

  public void setMinValue(final Double minValue) {
    this.minValue = minValue;
  }

  public Double getMaxValue() {
    return this.maxValue;
  }

  public void setMaxValue(final Double maxValue) {
    this.maxValue = maxValue;
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
