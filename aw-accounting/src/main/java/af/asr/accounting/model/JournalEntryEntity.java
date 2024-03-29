
package af.asr.accounting.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unused", "WeakerAccess"})
@Entity
@Table(name = "acc_credit_type", schema = "accounting")
public class JournalEntryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @SuppressWarnings("DefaultAnnotationParam")
  @Column(name = "date_bucket")
  private String dateBucket;
  @SuppressWarnings("DefaultAnnotationParam")
  @Column(name = "transaction_identifier")
  private String transactionIdentifier;
  @Column(name = "transaction_date")
  private LocalDateTime transactionDate;
  @Column(name = "transaction_type")
  private String transactionType;
  @Column(name = "clerk")
  private String clerk;
  @Column(name = "note")
  private String note;
//  @Frozen
//  @Column(name = "debtors")
  @OneToMany
  private Set<DebtorType> debtors= new HashSet<>();
//  @Frozen
//  @Column(name = "creditors")
  @OneToMany
  private Set<CreditorType> creditors = new HashSet<>();
  @Column(name = "state")
  private String state;
  @Column(name = "message")
  private String message;
  @Column(name = "created_on")
  private LocalDateTime createdOn;
  @Column(name = "created_by")
  private String createdBy;

  public JournalEntryEntity() {
    super();
  }

  public String getDateBucket() {
    return this.dateBucket;
  }

  public void setDateBucket(final String dateBucket) {
    this.dateBucket = dateBucket;
  }

  public String getTransactionIdentifier() {
    return this.transactionIdentifier;
  }

  public void setTransactionIdentifier(final String transactionIdentifier) {
    this.transactionIdentifier = transactionIdentifier;
  }

  public LocalDateTime getTransactionDate() {
    return this.transactionDate;
  }

  public void setTransactionDate(final LocalDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getTransactionType() {
    return this.transactionType;
  }

  public void setTransactionType(final String transactionType) {
    this.transactionType = transactionType;
  }

  public String getClerk() {
    return this.clerk;
  }

  public void setClerk(final String clerk) {
    this.clerk = clerk;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(final String note) {
    this.note = note;
  }

  public Set<DebtorType> getDebtors() {
    return this.debtors;
  }

  public void setDebtors(final Set<DebtorType> debtors) {
    this.debtors = debtors;
  }

  public Set<CreditorType> getCreditors() {
    return this.creditors;
  }

  public void setCreditors(final Set<CreditorType> creditors) {
    this.creditors = creditors;
  }

  public String getState() {
    return this.state;
  }

  public void setState(final String state) {
    this.state = state;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(final String message) {
    this.message = message;
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
}
