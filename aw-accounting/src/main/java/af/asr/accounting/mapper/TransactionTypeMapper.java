
package af.asr.accounting.mapper;

import af.asr.accounting.domain.*;
import af.asr.accounting.model.*;
import af.gov.anar.lang.validation.date.*;

public class TransactionTypeMapper {

  private TransactionTypeMapper() {
    super();
  }

  public static TransactionType map(final TransactionTypeEntity transactionTypeEntity) {
    final TransactionType transactionType = new TransactionType();
    transactionType.setCode(transactionTypeEntity.getIdentifier());
    transactionType.setName(transactionTypeEntity.getName());
    transactionType.setDescription(transactionTypeEntity.getDescription());

    return transactionType;
  }

  public static TransactionTypeEntity map(final TransactionType transactionType) {
    final TransactionTypeEntity transactionTypeEntity = new TransactionTypeEntity();
    transactionTypeEntity.setIdentifier(transactionType.getCode());
    transactionTypeEntity.setName(transactionType.getName());
    transactionTypeEntity.setDescription(transactionType.getDescription());

    return transactionTypeEntity;
  }
}
