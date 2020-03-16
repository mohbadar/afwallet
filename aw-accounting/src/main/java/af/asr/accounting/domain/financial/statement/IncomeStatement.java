
package af.asr.accounting.domain.financial.statement;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IncomeStatement {

  @NotEmpty
  private String date;
  @NotEmpty
  private List<IncomeStatementSection> incomeStatementSections = new ArrayList<>();
  @NotNull
  private BigDecimal grossProfit;
  @NotNull
  private BigDecimal totalExpenses;
  @NotNull
  private BigDecimal netIncome;

  public IncomeStatement() {
    super();
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(final String date) {
    this.date = date;
  }

  public List<IncomeStatementSection> getIncomeStatementSections() {
    return this.incomeStatementSections;
  }

  public BigDecimal getGrossProfit() {
    return this.grossProfit;
  }

  public void setGrossProfit(final BigDecimal grossProfit) {
    this.grossProfit = grossProfit;
  }

  public BigDecimal getTotalExpenses() {
    return this.totalExpenses;
  }

  public void setTotalExpenses(final BigDecimal totalExpenses) {
    this.totalExpenses = totalExpenses;
  }

  public BigDecimal getNetIncome() {
    return this.netIncome;
  }

  public void setNetIncome(final BigDecimal netIncome) {
    this.netIncome = netIncome;
  }

  public void add(final IncomeStatementSection incomeStatementSection) {
    this.incomeStatementSections.add(incomeStatementSection);
  }
}
