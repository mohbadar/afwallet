
package af.asr.accounting.domain;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class TransactionTypePage {

  @Valid
  private List<TransactionType> transactionTypes;
  private Integer totalPages;
  private Long totalElements;

  public TransactionTypePage() {
    super();
  }

  public List<TransactionType> getTransactionTypes() {
    return this.transactionTypes;
  }

  public void setTransactionTypes(final List<TransactionType> transactionTypes) {
    this.transactionTypes = transactionTypes;
  }

  public Integer getTotalPages() {
    return this.totalPages;
  }

  public void setTotalPages(final Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Long getTotalElements() {
    return this.totalElements;
  }

  public void setTotalElements(final Long totalElements) {
    this.totalElements = totalElements;
  }

  public void add(final TransactionType transactionType) {
    if (this.transactionTypes == null) {
      this.transactionTypes = new ArrayList<>();
    }

    this.transactionTypes.add(transactionType);
  }
}
