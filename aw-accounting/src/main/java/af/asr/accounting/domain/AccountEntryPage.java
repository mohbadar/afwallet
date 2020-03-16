
package af.asr.accounting.domain;

import java.util.List;

@SuppressWarnings("unused")
public class AccountEntryPage {
  private List<AccountEntry> accountEntries;
  private Integer totalPages;
  private Long totalElements;

  public AccountEntryPage() {
    super();
  }

  public List<AccountEntry> getAccountEntries() {
    return accountEntries;
  }

  public void setAccountEntries(List<AccountEntry> accountEntries) {
    this.accountEntries = accountEntries;
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
