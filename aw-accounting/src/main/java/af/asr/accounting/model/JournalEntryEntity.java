
package af.asr.accounting.model;

import com.datastax.driver.mapping.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;

@SuppressWarnings({"unused", "WeakerAccess"})
@Table(name = "acc_journal_entries", schema = "accounting")
public class JournalEntryEntity {

  @SuppressWarnings("DefaultAnnotationParam")
  @PartitionKey(value = 0)
  @Column(name = "date_bucket")
  private String dateBucket;
  @SuppressWarnings("DefaultAnnotationParam")
  @ClusteringColumn(value = 0)
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
  @Frozen
  @Column(name = "debtors")
  private Set<DebtorType> debtors;
  @Frozen
  @Column(name = "creditors")
  private Set<CreditorType> creditors;
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
