
package af.asr.accounting.domain;

import java.util.List;

@SuppressWarnings("unused")
public class LedgerPage {

  private List<Ledger> ledgers;
  private Integer totalPages;
  private Long totalElements;

  public LedgerPage() {
    super();
  }

  public List<Ledger> getLedgers() {
    return ledgers;
  }

  public void setLedgers(List<Ledger> ledgers) {
    this.ledgers = ledgers;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }
}
