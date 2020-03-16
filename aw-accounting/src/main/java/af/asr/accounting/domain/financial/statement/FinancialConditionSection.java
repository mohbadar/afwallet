
package af.asr.accounting.domain.financial.statement;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinancialConditionSection {

  public enum Type {
    ASSET,
    EQUITY,
    LIABILITY
  }

  @NotEmpty
  private Type type;
  @NotEmpty
  private String description;
  @NotEmpty
  private List<FinancialConditionEntry> financialConditionEntries = new ArrayList<>();
  @NotNull
  private BigDecimal subtotal = BigDecimal.ZERO;

  public FinancialConditionSection() {
    super();
  }

  public String getType() {
    return this.type.name();
  }

  public void setType(final String type) {
    this.type = Type.valueOf(type);
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public List<FinancialConditionEntry> getFinancialConditionEntries() {
    return this.financialConditionEntries;
  }

  public BigDecimal getSubtotal() {
    return this.subtotal;
  }

  public void add(final FinancialConditionEntry financialConditionEntry) {
    this.financialConditionEntries.add(financialConditionEntry);
    this.subtotal = this.subtotal.add(financialConditionEntry.getValue());
  }
}
