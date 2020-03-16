
package af.asr.accounting.model;

import af.asr.infrastructure.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings({"unused"})
@Entity
@Table(name = "acc_account_entries", schema = "accounting")
public class AccountEntryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "account_id")
	private AccountEntity account;
	@Column(name = "a_type")
	private String type;
	@Column(name = "transaction_date")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime transactionDate;
	@Column(name = "message")
	private String message;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "balance")
	private Double balance;
	
	public AccountEntryEntity() {
		super();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public AccountEntity getAccount() {
		return this.account;
	}
	
	public void setAccount(final AccountEntity account) {
		this.account = account;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}
	
	public LocalDateTime getTransactionDate() {
		return this.transactionDate;
	}
	
	public void setTransactionDate(final LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(final String message) {
		this.message = message;
	}
	
	public Double getAmount() {
		return this.amount;
	}
	
	public void setAmount(final Double amount) {
		this.amount = amount;
	}
	
	public Double getBalance() {
		return this.balance;
	}
	
	public void setBalance(final Double balance) {
		this.balance = balance;
	}
}
