
package af.asr.customer.catalog.domain;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;
import org.hibernate.validator.constraints.NotEmpty;

public class Value {

  @ValidIdentifier
  private String catalogIdentifier;
  @ValidIdentifier
  private String fieldIdentifier;
  @NotEmpty
  private String value;

  public Value() {
    super();
  }

  public String getCatalogIdentifier() {
    return this.catalogIdentifier;
  }

  public void setCatalogIdentifier(final String catalogIdentifier) {
    this.catalogIdentifier = catalogIdentifier;
  }

  public String getFieldIdentifier() {
    return this.fieldIdentifier;
  }

  public void setFieldIdentifier(final String fieldIdentifier) {
    this.fieldIdentifier = fieldIdentifier;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(final String value) {
    this.value = value;
  }
}
