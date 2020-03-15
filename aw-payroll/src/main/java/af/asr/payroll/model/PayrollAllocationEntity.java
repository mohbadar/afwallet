
package af.asr.payroll.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "payroll_payroll_allocations", schema = "payroll")
public class PayrollAllocationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "payroll_configuration_id", nullable = false)
  private PayrollConfigurationEntity payrollConfiguration;
  @Column(name = "account_number", nullable = false, length = 34)
  private String accountNumber;
  @Column(name = "amount", nullable = false, precision = 15, scale = 5)
  private BigDecimal amount;
  @Column(name = "proportional", nullable = false)
  private Boolean proportional = Boolean.FALSE;

  public PayrollAllocationEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public PayrollConfigurationEntity getPayrollConfiguration() {
    return this.payrollConfiguration;
  }

  public void setPayrollConfiguration(final PayrollConfigurationEntity payrollConfiguration) {
    this.payrollConfiguration = payrollConfiguration;
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
}
