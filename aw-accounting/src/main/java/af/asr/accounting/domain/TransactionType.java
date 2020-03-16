
package af.asr.accounting.domain;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;
import org.hibernate.validator.constraints.NotEmpty;

public class TransactionType {

  @ValidIdentifier
  private String code;
  @NotEmpty
  private String name;
  private String description;

  public TransactionType() {
    super();
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(final String code) {
    this.code = code;
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
