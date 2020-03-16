
package af.asr.accounting.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@SuppressWarnings({"unused", "WeakerAccess"})
@Table(name = "acc_journal_entry_lookup", schema = "accounting")
public class JournalEntryLookup {

  @PartitionKey
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
