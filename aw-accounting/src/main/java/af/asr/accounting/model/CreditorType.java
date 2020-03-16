
package af.asr.accounting.model;


import javax.persistence.*;

@SuppressWarnings({"unused"})
@Entity
@Table(name = "acc_credit_type", schema = "accounting")
public class CreditorType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "account_number")
  private String accountNumber;
  @Column(name = "amount")
  private Double amount;

  public CreditorType() {
    super();
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public void setAccountNumber(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(final Double amount) {
    this.amount = amount;
  }
}
