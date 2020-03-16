
package af.asr.accounting.domain;

import java.util.List;

@SuppressWarnings("unused")
public class AccountPage {
  private List<Account> accounts;
  private Integer totalPages;
  private Long totalElements;

  public AccountPage() {
    super();
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
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
