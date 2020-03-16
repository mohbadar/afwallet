
package af.asr.accounting.mapper;

import af.asr.accounting.domain.*;
import af.asr.accounting.model.*;
import af.gov.anar.lang.validation.date.*;

import java.math.BigDecimal;

public class LedgerMapper {

  private LedgerMapper() {
    super();
  }

  public static Ledger map(final LedgerEntity ledgerEntity) {
    final Ledger ledger = new Ledger();
    ledger.setType(ledgerEntity.getType());
    ledger.setIdentifier(ledgerEntity.getIdentifier());
    ledger.setName(ledgerEntity.getName());
    ledger.setDescription(ledgerEntity.getDescription());
    if (ledgerEntity.getParentLedger() != null) {
      ledger.setParentLedgerIdentifier(ledgerEntity.getParentLedger().getIdentifier());
    }
    ledger.setCreatedBy(ledgerEntity.getCreatedBy());
    ledger.setCreatedOn(DateConverter.toIsoString(ledgerEntity.getCreatedOn()));
    if (ledgerEntity.getLastModifiedBy() != null) {
      ledger.setLastModifiedBy(ledgerEntity.getLastModifiedBy());
      ledger.setLastModifiedOn(DateConverter.toIsoString(ledgerEntity.getLastModifiedOn()));
    }
    ledger.setShowAccountsInChart(ledgerEntity.getShowAccountsInChart());
    final BigDecimal totalValue = ledgerEntity.getTotalValue() != null ? ledgerEntity.getTotalValue() : BigDecimal.ZERO;
    ledger.setTotalValue(totalValue);
    return ledger;
  }
}
