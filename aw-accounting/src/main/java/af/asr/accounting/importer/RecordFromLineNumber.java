
package af.asr.accounting.importer;

/**
 * @author Myrle Krantz
 */
class RecordFromLineNumber<T> {

  private final long recordNumber;
  private final T record;

  RecordFromLineNumber(final long recordNumber, final T record) {
    this.recordNumber = recordNumber;
    this.record = record;
  }

  long getRecordNumber() {
    return recordNumber;
  }

  T getRecord() {
    return record;
  }
}