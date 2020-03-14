
package af.asr.catalog.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Option {

  @NotEmpty
  private String label;
  @NotNull
  private Integer value;
  private String createdBy;
  private String createdOn;

  public Option() {
    super();
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

  public String getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(final String createdOn) {
    this.createdOn = createdOn;
  }
}
