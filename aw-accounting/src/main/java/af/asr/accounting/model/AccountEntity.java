
package af.asr.accounting.model;

import af.asr.infrastructure.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings({"unused"})
@Entity
@Table(name = "acc_accounts", schema = "accounting")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "a_type")
  private String type;
  @Column(name = "identifier")
  private String identifier;
  @Column(name = "a_name")
  private String name;
  @Column(name = "holders")
  private String holders;
  @Column(name = "signature_authorities")
  private String signatureAuthorities;
  @Column(name = "balance")
  private Double balance;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reference_account_id")
  private AccountEntity referenceAccount;
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "ledger_id")
  private LedgerEntity ledger;
  @Column(name = "a_state")
  private String state;
  @Column(name = "alternative_account_number", length = 256, nullable = true)
  private String alternativeAccountNumber;
  @Column(name = "created_on")
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime createdOn;
  @Column(name = "created_by")
  private String createdBy;
  @Column(name = "last_modified_on")
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime lastModifiedOn;
  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  public AccountEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getHolders() {
    return this.holders;
  }

  public void setHolders(final String holders) {
    this.holders = holders;
  }

  public String getSignatureAuthorities() {
    return this.signatureAuthorities;
  }

  public void setSignatureAuthorities(final String signatureAuthorities) {
    this.signatureAuthorities = signatureAuthorities;
  }

  public Double getBalance() {
    return this.balance;
  }

  public void setBalance(final Double balance) {
    this.balance = balance;
  }

  public AccountEntity getReferenceAccount() {
    return this.referenceAccount;
  }

  public void setReferenceAccount(final AccountEntity referenceAccount) {
    this.referenceAccount = referenceAccount;
  }

  public LedgerEntity getLedger() {
    return this.ledger;
  }

  public void setLedger(final LedgerEntity ledger) {
    this.ledger = ledger;
  }

  public String getState() {
    return this.state;
  }

  public void setState(final String state) {
    this.state = state;
  }

  public String getAlternativeAccountNumber() {
    return this.alternativeAccountNumber;
  }

  public void setAlternativeAccountNumber(final String alternativeAccountNumber) {
    this.alternativeAccountNumber = alternativeAccountNumber;
  }

  public LocalDateTime getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(final LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(final String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getLastModifiedOn() {
    return this.lastModifiedOn;
  }

  public void setLastModifiedOn(final LocalDateTime lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public void setLastModifiedBy(final String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final AccountEntity that = (AccountEntity) o;

    return identifier.equals(that.identifier);

  }

  @Override
  public int hashCode() {
    return identifier.hashCode();
  }
}
