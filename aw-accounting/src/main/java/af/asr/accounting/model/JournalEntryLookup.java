
package af.asr.accounting.model;



import javax.persistence.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@Entity
@Table(name = "acc_journal_entry_lookup", schema = "accounting")
public class JournalEntryLookup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "transaction_identifier")
  private String transactionIdentifier;
  @Column(name = "date_bucket")
  private String dateBucket;

  public JournalEntryLookup() {
    super();
  }

  public String getTransactionIdentifier() {
    return this.transactionIdentifier;
  }

  public void setTransactionIdentifier(final String transactionIdentifier) {
    this.transactionIdentifier = transactionIdentifier;
  }

  public String getDateBucket() {
    return this.dateBucket;
  }

  public void setDateBucket(final String dateBucket) {
    this.dateBucket = dateBucket;
  }
}
