
package af.asr.accounting.domain.financial.statement;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinancialCondition {

  @NotEmpty
  private String date;
  @NotEmpty
  private List<FinancialConditionSection> financialConditionSections = new ArrayList<>();
  @NotNull
  private BigDecimal totalAssets;
  @NotNull
  private BigDecimal totalEquitiesAndLiabilities;

  public FinancialCondition() {
    super();
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(final String date) {
    this.date = date;
  }

  public List<FinancialConditionSection> getFinancialConditionSections() {
    return this.financialConditionSections;
  }

  public BigDecimal getTotalAssets() {
    return this.totalAssets;
  }

  public void setTotalAssets(final BigDecimal totalAssets) {
    this.totalAssets = totalAssets;
  }

  public BigDecimal getTotalEquitiesAndLiabilities() {
    return this.totalEquitiesAndLiabilities;
  }

  public void setTotalEquitiesAndLiabilities(final BigDecimal totalEquitiesAndLiabilities) {
    this.totalEquitiesAndLiabilities = totalEquitiesAndLiabilities;
  }

  public void add(final FinancialConditionSection financialConditionSection) {
    this.financialConditionSections.add(financialConditionSection);
  }
}
