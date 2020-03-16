
package af.asr.accounting.service;

import af.asr.accounting.ServiceConstants;
import af.asr.accounting.domain.*;
import af.asr.accounting.mapper.*;
import af.asr.accounting.model.*;
import af.asr.accounting.repository.*;
import af.asr.infrastructure.service.UserService;
import af.gov.anar.lang.validation.date.DateConverter;
import af.gov.anar.lang.validation.date.DateRange;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JournalEntryService {

  private Logger logger;
  private final JournalEntryRepository journalEntryRepository;
  private final TransactionTypeRepository transactionTypeRepository;
  private final UserService userService;
  private final AccountService accountService;

  @Autowired
  public JournalEntryService(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
                             final JournalEntryRepository journalEntryRepository,
                             final TransactionTypeRepository transactionTypeRepository, UserService userService, AccountService accountService) {
    super();
    this.logger = logger;
    this.journalEntryRepository = journalEntryRepository;
    this.transactionTypeRepository = transactionTypeRepository;
      this.userService = userService;
      this.accountService = accountService;
  }

  public List<JournalEntry> fetchJournalEntries(final DateRange range, final String accountNumber, final BigDecimal amount) {
    final List<JournalEntryEntity> journalEntryEntities = this.journalEntryRepository.findAll();
//        this.journalEntryRepository.fetchJournalEntries(range);

    if (journalEntryEntities != null) {

      final List<JournalEntryEntity> filteredList =
          journalEntryEntities
              .stream()
              .filter(journalEntryEntity ->
                  accountNumber == null
                      || journalEntryEntity.getDebtors().stream()
                          .anyMatch(debtorType -> debtorType.getAccountNumber().equals(accountNumber))
                      || journalEntryEntity.getCreditors().stream()
                          .anyMatch(creditorType -> creditorType.getAccountNumber().equals(accountNumber))
              )
              .filter(journalEntryEntity ->
                  amount == null
                      || amount.compareTo(
                          BigDecimal.valueOf(
                              journalEntryEntity.getDebtors().stream().mapToDouble(DebtorType::getAmount).sum()
                          )
                  ) == 0
              )
              .sorted(Comparator.comparing(JournalEntryEntity::getTransactionDate))
              .collect(Collectors.toList());

      final List<TransactionTypeEntity> transactionTypes = this.transactionTypeRepository.findAll();
      final HashMap<String, String> mappedTransactionTypes = new HashMap<>(transactionTypes.size());
      transactionTypes.forEach(transactionTypeEntity ->
          mappedTransactionTypes.put(transactionTypeEntity.getIdentifier(), transactionTypeEntity.getName())
      );

      return filteredList
          .stream()
          .map(journalEntryEntity -> {
            final JournalEntry journalEntry = JournalEntryMapper.map(journalEntryEntity);
            journalEntry.setTransactionType(mappedTransactionTypes.get(journalEntry.getTransactionType()));
            return journalEntry;
          })
          .collect(Collectors.toList());
    } else {
      return Collections.emptyList();
    }
  }

  public Optional<JournalEntry> findJournalEntry(final String transactionIdentifier) {
    final Optional<JournalEntryEntity> optionalJournalEntryEntity = this.journalEntryRepository.findByTransactionIdentifier(transactionIdentifier);

    return optionalJournalEntryEntity.map(JournalEntryMapper::map);
  }

    @Transactional
    public String createJournalEntry(final JournalEntry journalEntry) {
        final Set<Debtor> debtors = journalEntry.getDebtors();
        final Set<DebtorType> debtorTypes = debtors
                .stream()
                .map(debtor -> {
                    final DebtorType debtorType = new DebtorType();
                    debtorType.setAccountNumber(debtor.getAccountNumber());
                    debtorType.setAmount(Double.valueOf(debtor.getAmount()));
                    return debtorType;
                })
                .collect(Collectors.toSet());
        final Set<Creditor> creditors = journalEntry.getCreditors();
        final Set<CreditorType> creditorTypes = creditors
                .stream()
                .map(creditor -> {
                    final CreditorType creditorType = new CreditorType();
                    creditorType.setAccountNumber(creditor.getAccountNumber());
                    creditorType.setAmount(Double.valueOf(creditor.getAmount()));
                    return creditorType;
                })
                .collect(Collectors.toSet());
        final JournalEntryEntity journalEntryEntity = new JournalEntryEntity();
        journalEntryEntity.setTransactionIdentifier(journalEntry.getTransactionIdentifier());
        final LocalDateTime transactionDate = DateConverter.fromIsoString(journalEntry.getTransactionDate());
        journalEntryEntity.setDateBucket(DateConverter.toIsoString(DateConverter.toLocalDate(transactionDate)));
        journalEntryEntity.setTransactionDate(transactionDate);
        journalEntryEntity.setTransactionType(journalEntry.getTransactionType());
        journalEntryEntity.setClerk(journalEntry.getClerk() != null ? journalEntry.getClerk() : userService.getPreferredUsername());
        journalEntryEntity.setNote(journalEntry.getNote());
        journalEntryEntity.setDebtors(debtorTypes);
        journalEntryEntity.setCreditors(creditorTypes);
        journalEntryEntity.setMessage(journalEntry.getMessage());
        journalEntryEntity.setState(JournalEntry.State.PENDING.name());
        journalEntryEntity.setCreatedBy(userService.getPreferredUsername());
        journalEntryEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
        journalEntryRepository.save(journalEntryEntity);
        this.accountService.bookJournalEntry(journalEntry.getTransactionIdentifier());
        return journalEntry.getTransactionIdentifier();
    }


}
