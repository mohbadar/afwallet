
package af.asr.accounting.domain.financial.statement;


import af.asr.accounting.domain.Ledger;

import java.math.BigDecimal;

@SuppressWarnings("WeakerAccess")
public class TrialBalanceEntry {

  private Ledger ledger;
  private Type type;
  private BigDecimal amount;

  public TrialBalanceEntry() {
    super();
  }

  public Ledger getLedger() {
    return this.ledger;
  }

  public void setLedger(final Ledger ledger) {
    this.ledger = ledger;
  }

  public String getType() {
    return this.type.name();
  }

  public void setType(final String type) {
    this.type = Type.valueOf(type);
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(final BigDecimal amount) {
    this.amount = amount;
  }

  public enum Type {
    DEBIT,
    CREDIT
  }
}
