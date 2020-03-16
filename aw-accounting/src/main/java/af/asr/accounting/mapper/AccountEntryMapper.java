
package af.asr.accounting.mapper;


import af.asr.accounting.domain.*;
import af.asr.accounting.model.*;
import af.gov.anar.lang.validation.date.*;

public class AccountEntryMapper {

  private AccountEntryMapper() {
    super();
  }

  public static AccountEntry map(final AccountEntryEntity accountEntity) {
    final AccountEntry entry = new AccountEntry();

    entry.setType(accountEntity.getType());
    entry.setBalance(accountEntity.getBalance());
    entry.setAmount(accountEntity.getAmount());
    entry.setMessage(accountEntity.getMessage());
    entry.setTransactionDate(DateConverter.toIsoString(accountEntity.getTransactionDate()));

    return entry;
  }
}
