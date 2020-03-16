
package af.asr.accounting.domain.financial.statement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class TrialBalance {

  private List<TrialBalanceEntry> trialBalanceEntries;
  private BigDecimal debitTotal;
  private BigDecimal creditTotal;

  public TrialBalance() {
    super();
  }

  public List<TrialBalanceEntry> getTrialBalanceEntries() {
    if (this.trialBalanceEntries == null) {
      this.trialBalanceEntries = new ArrayList<>();
    }
    return this.trialBalanceEntries;
  }

  public void setTrialBalanceEntries(final List<TrialBalanceEntry> trialBalanceEntries) {
    this.trialBalanceEntries = trialBalanceEntries;
  }

  public BigDecimal getDebitTotal() {
    return this.debitTotal;
  }

  public void setDebitTotal(final BigDecimal debitTotal) {
    this.debitTotal = debitTotal;
  }

  public BigDecimal getCreditTotal() {
    return this.creditTotal;
  }

  public void setCreditTotal(final BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
  }
}
