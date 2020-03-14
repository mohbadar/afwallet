 // @Permittable
package af.asr.customer.events;

import java.util.Objects;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings("unused")
public class DocumentPageEvent {

  private String customerIdentifier;

  private String documentIdentifier;

  private int pageNumber;

  public DocumentPageEvent(String customerIdentifier, String documentIdentifier, int pageNumber) {
    this.customerIdentifier = customerIdentifier;
    this.documentIdentifier = documentIdentifier;
    this.pageNumber = pageNumber;
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

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DocumentPageEvent that = (DocumentPageEvent) o;
    return pageNumber == that.pageNumber &&
        Objects.equals(customerIdentifier, that.customerIdentifier) &&
        Objects.equals(documentIdentifier, that.documentIdentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerIdentifier, documentIdentifier, pageNumber);
  }

  @Override
  public String toString() {
    return "DocumentPageEvent{" +
        "customerIdentifier='" + customerIdentifier + '\'' +
        ", documentIdentifier='" + documentIdentifier + '\'' +
        ", pageNumber=" + pageNumber +
        '}';
  }
}
