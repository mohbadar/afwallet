 // @Permittable
package af.asr.customer.events;

import java.util.Objects;

/**
 * @author Myrle Krantz
 */
public class DocumentEvent {

  private String customerIdentifier;

  private String documentIdentifier;

  public DocumentEvent(String customerIdentifier, String documentIdentifier) {
    this.customerIdentifier = customerIdentifier;
    this.documentIdentifier = documentIdentifier;
  }

  public String getCustomerIdentifier() {
    return customerIdentifier;
  }

  public void setCustomerIdentifier(String customerIdentifier) {
    this.customerIdentifier = customerIdentifier;
  }

  public String getDocumentIdentifier() {
    return documentIdentifier;
  }

  public void setDocumentIdentifier(String documentIdentifier) {
    this.documentIdentifier = documentIdentifier;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DocumentEvent that = (DocumentEvent) o;
    return Objects.equals(customerIdentifier, that.customerIdentifier) &&
        Objects.equals(documentIdentifier, that.documentIdentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerIdentifier, documentIdentifier);
  }

  @Override
  public String toString() {
    return "DocumentEvent{" +
        "customerIdentifier='" + customerIdentifier + '\'' +
        ", documentIdentifier='" + documentIdentifier + '\'' +
        '}';
  }
}
