
package af.asr.csc.domain;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

public class CustomerDocument {
  @ValidIdentifier
  private String identifier;

  @Length(max = 4096)
  private String description;

  private boolean completed;
  private String createdBy;
  private String createdOn;

  public CustomerDocument() {
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(String createdOn) {
    this.createdOn = createdOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerDocument that = (CustomerDocument) o;
    return completed == that.completed &&
        Objects.equals(identifier, that.identifier) &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifier, description, completed);
  }

  @Override
  public String toString() {
    return "CustomerDocument{" +
        "identifier='" + identifier + '\'' +
        ", description='" + description + '\'' +
        ", completed=" + completed +
        ", createdBy='" + createdBy + '\'' +
        ", createdOn='" + createdOn + '\'' +
        '}';
  }
}
