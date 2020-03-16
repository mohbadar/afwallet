
package af.asr.accounting.service;

import af.asr.accounting.domain.*;
import af.asr.accounting.domain.financial.statement.FinancialCondition;
import af.asr.accounting.domain.financial.statement.FinancialConditionEntry;
import af.asr.accounting.domain.financial.statement.FinancialConditionSection;
import af.asr.accounting.mapper.*;
import af.asr.accounting.model.*;
import af.asr.accounting.repository.*;
import af.gov.anar.lang.validation.date.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.EnumSet;

@Service
public class FinancialConditionService {

  private final LedgerRepository ledgerRepository;

  @Autowired
  public FinancialConditionService(final LedgerRepository ledgerRepository) {
    super();
    this.ledgerRepository = ledgerRepository;
  }

  public FinancialCondition getFinancialCondition() {
    final FinancialCondition financialCondition = new FinancialCondition();
    financialCondition.setDate(DateConverter.toIsoString(LocalDateTime.now(Clock.systemUTC())));

    this.createFinancialConditionSection(financialCondition, AccountType.ASSET, FinancialConditionSection.Type.ASSET);
    this.createFinancialConditionSection(financialCondition, AccountType.EQUITY, FinancialConditionSection.Type.EQUITY);
    this.createFinancialConditionSection(financialCondition, AccountType.LIABILITY, FinancialConditionSection.Type.LIABILITY);

    financialCondition.setTotalAssets(
        this.calculateTotal(financialCondition,
            EnumSet.of(FinancialConditionSection.Type.ASSET))
    );
    financialCondition.setTotalEquitiesAndLiabilities(
        this.calculateTotal(financialCondition,
            EnumSet.of(FinancialConditionSection.Type.EQUITY, FinancialConditionSection.Type.LIABILITY))
    );

    return financialCondition;
  }

  private void createFinancialConditionSection(final FinancialCondition financialCondition, final AccountType accountType,
                                               final FinancialConditionSection.Type financialConditionType) {
    this.ledgerRepository.findByParentLedgerIsNullAndType(accountType.name()).forEach(ledgerEntity -> {
      final FinancialConditionSection financialConditionSection = new FinancialConditionSection();
      financialConditionSection.setType(financialConditionType.name());
      financialConditionSection.setDescription(ledgerEntity.getName());
      financialCondition.add(financialConditionSection);

      this.ledgerRepository.findByParentLedgerOrderByIdentifier(ledgerEntity).forEach(subLedgerEntity -> {
        final FinancialConditionEntry financialConditionEntry = new FinancialConditionEntry();
        financialConditionEntry.setDescription(subLedgerEntity.getName());
        final BigDecimal totalValue = subLedgerEntity.getTotalValue() != null ? subLedgerEntity.getTotalValue() : BigDecimal.ZERO;
        financialConditionEntry.setValue(totalValue);
        financialConditionSection.add(financialConditionEntry);
      });
    });
  }

  private BigDecimal calculateTotal(final FinancialCondition financialCondition,
                                    final EnumSet<FinancialConditionSection.Type> financialConditionTypes) {
    return financialCondition.getFinancialConditionSections()
        .stream()
        .filter(financialConditionSection ->
            financialConditionTypes.contains(FinancialConditionSection.Type.valueOf(financialConditionSection.getType())))
        .map(FinancialConditionSection::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
