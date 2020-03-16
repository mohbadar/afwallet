
package af.asr.payroll.domain;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PayrollAllocation {

  @ValidIdentifier(maxLength = 34)
  private String accountNumber;
  @NotNull
  @DecimalMin("0.001")
  @DecimalMax("9999999999.99999")
  private BigDecimal amount;
  private Boolean proportional = Boolean.FALSE;

  public PayrollAllocation() {
    super();
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public void setAccountNumber(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(final BigDecimal amount) {
    this.amount = amount;
  }

  public Boolean getProportional() {
    return this.proportional;
  }

  public void setProportional(final Boolean proportional) {
    this.proportional = proportional;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final PayrollAllocation that = (PayrollAllocation) o;

    return accountNumber != null ? accountNumber.equals(that.accountNumber) : that.accountNumber == null;
  }

  @Override
  public int hashCode() {
    return accountNumber != null ? accountNumber.hashCode() : 0;
  }
}
