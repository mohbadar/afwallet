
package af.asr.accounting.service;

import af.asr.accounting.domain.*;
import af.asr.accounting.mapper.*;
import af.asr.accounting.model.*;
import af.asr.accounting.repository.*;
import af.asr.accounting.specification.AccountSpecification;
import af.asr.infrastructure.service.UserService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import af.gov.anar.lang.validation.date.DateRange;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountEntryRepository accountEntryRepository;
  private final CommandRepository commandRepository;
  private final Logger logger;
  private final LedgerRepository ledgerRepository;
  private final JournalEntryRepository journalEntryRepository;
  private final UserService userService;

  @Autowired
  public AccountService(final AccountRepository accountRepository,
                        final AccountEntryRepository accountEntryRepository,
                        final CommandRepository commandRepository, Logger logger, LedgerRepository ledgerRepository,
                        JournalEntryRepository journalEntryRepository, UserService userService) {
    super();
    this.accountRepository = accountRepository;
    this.accountEntryRepository = accountEntryRepository;
    this.commandRepository = commandRepository;
    this.logger = logger;
    this.ledgerRepository = ledgerRepository;
    this.journalEntryRepository = journalEntryRepository;
    this.userService = userService;
  }

  public Optional<Account> findAccount(final String identifier) {
    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    if (accountEntity == null) {
      return Optional.empty();
    } else {
      return Optional.of(AccountMapper.map(accountEntity));
    }
  }

  public AccountPage fetchAccounts(
      final boolean includeClosed, final String term, final String type,
      final boolean includeCustomerAccounts, final Pageable pageable) {

    final Page<AccountEntity> accountEntities = this.accountRepository.findAll(
        AccountSpecification.createSpecification(includeClosed, term, type, includeCustomerAccounts), pageable
    );

    final AccountPage accountPage = new AccountPage();
    accountPage.setTotalPages(accountEntities.getTotalPages());
    accountPage.setTotalElements(accountEntities.getTotalElements());

    if(accountEntities.getSize() > 0){
      final List<Account> accounts = new ArrayList<>(accountEntities.getSize());
      accountEntities.forEach(accountEntity -> accounts.add(AccountMapper.map(accountEntity)));
      accountPage.setAccounts(accounts);
    }

    return accountPage;

  }

  public AccountEntryPage fetchAccountEntries(final String identifier,
                                              final DateRange range,
                                              final @Nullable String message,
                                              final Pageable pageable){

    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);

    final Page<AccountEntryEntity> accountEntryEntities;
    if (message == null) {
      accountEntryEntities = this.accountEntryRepository.findByAccountAndTransactionDateBetween(
          accountEntity, range.getStartDateTime(), range.getEndDateTime(), pageable);
    }
    else {
      accountEntryEntities = this.accountEntryRepository.findByAccountAndTransactionDateBetweenAndMessageEquals(
          accountEntity, range.getStartDateTime(), range.getEndDateTime(), message, pageable);
    }

    final AccountEntryPage accountEntryPage = new AccountEntryPage();
    accountEntryPage.setTotalPages(accountEntryEntities.getTotalPages());
    accountEntryPage.setTotalElements(accountEntryEntities.getTotalElements());

    if(accountEntryEntities.getSize() > 0){
      final List<AccountEntry> accountEntries = new ArrayList<>(accountEntryEntities.getSize());
      accountEntryEntities.forEach(accountEntryEntity -> accountEntries.add(AccountEntryMapper.map(accountEntryEntity)));
      accountEntryPage.setAccountEntries(accountEntries);
    }

    return accountEntryPage;
  }

  public final List<AccountCommand> fetchCommandsByAccount(final String identifier) {
    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    final List<CommandEntity> commands = this.commandRepository.findByAccount(accountEntity);
    if (commands != null) {
      return commands.stream().map(AccountCommandMapper::map).collect(Collectors.toList());
    } else {
      return Collections.emptyList();
    }
  }

  public Boolean hasEntries(final String identifier) {
    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    return this.accountEntryRepository.existsByAccount(accountEntity);
  }

  public Boolean hasReferenceAccounts(final String identifier) {
    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    return this.accountRepository.existsByReference(accountEntity);
  }

  public List<AccountCommand> getActions(final String identifier) {
    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    final ArrayList<AccountCommand> commands = new ArrayList<>();
    final Account.State state = Account.State.valueOf(accountEntity.getState());
    switch (state) {
      case OPEN:
        commands.add(this.buildCommand(AccountCommand.Action.LOCK));
        commands.add(this.buildCommand(AccountCommand.Action.CLOSE));
        break;
      case LOCKED:
        commands.add(this.buildCommand(AccountCommand.Action.UNLOCK));
        commands.add(this.buildCommand(AccountCommand.Action.CLOSE));
        break;
      case CLOSED:
        commands.add(this.buildCommand(AccountCommand.Action.REOPEN));
        break;
    }
    return commands;
  }

  private AccountCommand buildCommand(final AccountCommand.Action action) {
    final AccountCommand accountCommand = new AccountCommand();
    accountCommand.setAction(action.name());
    return accountCommand;
  }

  @Transactional
  public String createAccount(final Account account) {
    final AccountEntity accountEntity = new AccountEntity();
    accountEntity.setIdentifier(account.getIdentifier());
    accountEntity.setName(account.getName());
    accountEntity.setType(account.getType());

    final LedgerEntity ledger = this.ledgerRepository.findByIdentifier(account.getLedger());
    accountEntity.setLedger(ledger);

    AccountEntity referenceAccount = null;
    if (account.getReferenceAccount() != null) {
      referenceAccount = this.accountRepository.findByIdentifier(account.getReferenceAccount());
      if (referenceAccount.getState().equals(Account.State.OPEN.name())) {
        accountEntity.setReferenceAccount(referenceAccount);
      } else {
        throw ServiceException.badRequest("Reference account {0} is not valid.", referenceAccount.getIdentifier());
      }
    }

    if (account.getHolders() != null) {
      accountEntity.setHolders(
              account.getHolders()
                      .stream()
                      .collect(Collectors.joining(","))
      );
    }

    if (account.getSignatureAuthorities() != null) {
      accountEntity.setSignatureAuthorities(
              account.getSignatureAuthorities()
                      .stream()
                      .collect(Collectors.joining(","))
      );
    }

    accountEntity.setBalance(account.getBalance());
    accountEntity.setState(Account.State.OPEN.name());
    accountEntity.setAlternativeAccountNumber(account.getAlternativeAccountNumber());
    accountEntity.setCreatedBy(userService.getPreferredUsername());
    accountEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));

    final AccountEntity savedAccountEntity = this.accountRepository.save(accountEntity);

    if (referenceAccount != null) {
      referenceAccount.setLastModifiedBy(userService.getPreferredUsername());
      referenceAccount.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));
      this.accountRepository.save(referenceAccount);
    }

    this.ledgerRepository.save(ledger);

    if (savedAccountEntity.getBalance() != null && savedAccountEntity.getBalance() != 0.00D) {
      this.adjustLedgerTotals(
              savedAccountEntity.getLedger().getIdentifier(), BigDecimal.valueOf(savedAccountEntity.getBalance()));
    }

    return account.getIdentifier();
  }

  @Transactional
  public String modifyAccount(final Account account) {
    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(account.getIdentifier());

    if (account.getName() != null) {
      accountEntity.setName(account.getName());
    }

    LedgerEntity ledger = null;
    if (!account.getLedger().equals(accountEntity.getLedger().getIdentifier())) {
      ledger = this.ledgerRepository.findByIdentifier(account.getLedger());
      accountEntity.setLedger(ledger);
    }

    AccountEntity referenceAccount = null;
    if (account.getReferenceAccount() != null) {
      if (!account.getReferenceAccount().equals(accountEntity.getReferenceAccount().getIdentifier())) {
        referenceAccount = this.accountRepository.findByIdentifier(account.getReferenceAccount());
        accountEntity.setReferenceAccount(referenceAccount);
      }
    } else {
      accountEntity.setReferenceAccount(null);
    }

    if (account.getHolders() != null) {
      accountEntity.setHolders(
              account.getHolders()
                      .stream()
                      .collect(Collectors.joining(","))
      );
    } else {
      accountEntity.setHolders(null);
    }

    if (account.getSignatureAuthorities() != null) {
      accountEntity.setSignatureAuthorities(
              account.getSignatureAuthorities()
                      .stream()
                      .collect(Collectors.joining(","))
      );
    } else {
      accountEntity.setSignatureAuthorities(null);
    }

    accountEntity.setLastModifiedBy(userService.getPreferredUsername());
    accountEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    this.accountRepository.save(accountEntity);

    if (referenceAccount != null) {
      referenceAccount.setLastModifiedBy(userService.getPreferredUsername());
      referenceAccount.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));
      this.accountRepository.save(referenceAccount);
    }

    if (ledger != null) {
      this.ledgerRepository.save(ledger);
    }

    return account.getIdentifier();
  }

  @Transactional
  public String closeAccount(final String identifier, final String comment) {
    final String modifyingUser = userService.getPreferredUsername();
    final LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    accountEntity.setState(Account.State.CLOSED.name());
    accountEntity.setLastModifiedBy(modifyingUser);
    accountEntity.setLastModifiedOn(now);
    this.accountRepository.save(accountEntity);

    final CommandEntity commandEntity = new CommandEntity();
    commandEntity.setType(AccountCommand.Action.CLOSE.name());
    commandEntity.setAccount(accountEntity);
    commandEntity.setComment(comment);
    commandEntity.setCreatedBy(modifyingUser);
    commandEntity.setCreatedOn(now);
    this.commandRepository.save(commandEntity);

    return identifier;
  }

  @Transactional
  public String lockAccount(final String identifier, final String comment) {
    final String modifyingUser = userService.getPreferredUsername();
    final LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    accountEntity.setState(Account.State.LOCKED.name());
    accountEntity.setLastModifiedBy(modifyingUser);
    accountEntity.setLastModifiedOn(now);
    this.accountRepository.save(accountEntity);

    final CommandEntity commandEntity = new CommandEntity();
    commandEntity.setType(AccountCommand.Action.LOCK.name());
    commandEntity.setAccount(accountEntity);
    commandEntity.setComment(comment);
    commandEntity.setCreatedBy(modifyingUser);
    commandEntity.setCreatedOn(now);
    this.commandRepository.save(commandEntity);

    return identifier;
  }

  @Transactional
  public String unlockAccount(final String identifier, final String comment ){
    final String modifyingUser = userService.getPreferredUsername();
    final LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    accountEntity.setState(Account.State.OPEN.name());
    accountEntity.setLastModifiedBy(modifyingUser);
    accountEntity.setLastModifiedOn(now);
    this.accountRepository.save(accountEntity);

    final CommandEntity commandEntity = new CommandEntity();
    commandEntity.setType(AccountCommand.Action.UNLOCK.name());
    commandEntity.setAccount(accountEntity);
    commandEntity.setComment(comment);
    commandEntity.setCreatedBy(modifyingUser);
    commandEntity.setCreatedOn(now);
    this.commandRepository.save(commandEntity);

    return identifier;
  }

  @Transactional
  public String reopenAccount(final String identifier, final String comment) {
    final String modifyingUser = userService.getPreferredUsername();
    final LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(identifier);
    accountEntity.setState(Account.State.OPEN.name());
    accountEntity.setLastModifiedBy(modifyingUser);
    accountEntity.setLastModifiedOn(now);
    this.accountRepository.save(accountEntity);

    final CommandEntity commandEntity = new CommandEntity();
    commandEntity.setType(AccountCommand.Action.REOPEN.name());
    commandEntity.setAccount(accountEntity);
    commandEntity.setComment(comment);
    commandEntity.setCreatedBy(modifyingUser);
    commandEntity.setCreatedOn(now);
    this.commandRepository.save(commandEntity);

    return identifier;
  }

  @Transactional
  public String bookJournalEntry(final String transactionIdentifier) {

    final Optional<JournalEntryEntity> optionalJournalEntry = this.journalEntryRepository.findByTransactionIdentifier(transactionIdentifier);
    if (optionalJournalEntry.isPresent()) {
      final JournalEntryEntity journalEntryEntity = optionalJournalEntry.get();
      if (!journalEntryEntity.getState().equals(JournalEntry.State.PENDING.name())) {
        return null;
      }
      // process all debtors
      journalEntryEntity.getDebtors()
              .forEach(debtor -> {
                final String accountNumber = debtor.getAccountNumber();
                final AccountEntity accountEntity = this.accountRepository.findByIdentifier(accountNumber);
                final AccountType accountType = AccountType.valueOf(accountEntity.getType());
                final BigDecimal amount;
                switch (accountType) {
                  case ASSET:
                  case EXPENSE:
                    accountEntity.setBalance(accountEntity.getBalance() + debtor.getAmount());
                    amount = BigDecimal.valueOf(debtor.getAmount());
                    break;
                  case LIABILITY:
                  case EQUITY:
                  case REVENUE:
                    accountEntity.setBalance(accountEntity.getBalance() - debtor.getAmount());
                    amount = BigDecimal.valueOf(debtor.getAmount()).negate();
                    break;
                  default:
                    amount = BigDecimal.ZERO;
                }
                final AccountEntity savedAccountEntity = this.accountRepository.save(accountEntity);
                final AccountEntryEntity accountEntryEntity = new AccountEntryEntity();
                accountEntryEntity.setType(AccountEntry.Type.DEBIT.name());
                accountEntryEntity.setAccount(savedAccountEntity);
                accountEntryEntity.setBalance(savedAccountEntity.getBalance());
                accountEntryEntity.setAmount(debtor.getAmount());
                accountEntryEntity.setMessage(journalEntryEntity.getMessage());
                accountEntryEntity.setTransactionDate(journalEntryEntity.getTransactionDate());
                this.accountEntryRepository.save(accountEntryEntity);
                this.adjustLedgerTotals(savedAccountEntity.getLedger().getIdentifier(), amount);
              });
      // process all creditors
      journalEntryEntity.getCreditors()
              .forEach(creditor -> {
                final String accountNumber = creditor.getAccountNumber();
                final AccountEntity accountEntity = this.accountRepository.findByIdentifier(accountNumber);
                final AccountType accountType = AccountType.valueOf(accountEntity.getType());
                final BigDecimal amount;
                switch (accountType) {
                  case ASSET:
                  case EXPENSE:
                    accountEntity.setBalance(accountEntity.getBalance() - creditor.getAmount());
                    amount = BigDecimal.valueOf(creditor.getAmount()).negate();
                    break;
                  case LIABILITY:
                  case EQUITY:
                  case REVENUE:
                    accountEntity.setBalance(accountEntity.getBalance() + creditor.getAmount());
                    amount = BigDecimal.valueOf(creditor.getAmount());
                    break;
                  default:
                    amount = BigDecimal.ZERO;
                }
                final AccountEntity savedAccountEntity = this.accountRepository.save(accountEntity);
                final AccountEntryEntity accountEntryEntity = new AccountEntryEntity();
                accountEntryEntity.setType(AccountEntry.Type.CREDIT.name());
                accountEntryEntity.setAccount(savedAccountEntity);
                accountEntryEntity.setBalance(savedAccountEntity.getBalance());
                accountEntryEntity.setAmount(creditor.getAmount());
                accountEntryEntity.setMessage(journalEntryEntity.getMessage());
                accountEntryEntity.setTransactionDate(journalEntryEntity.getTransactionDate());
                this.accountEntryRepository.save(accountEntryEntity);
                this.adjustLedgerTotals(savedAccountEntity.getLedger().getIdentifier(), amount);
              });
      this.releaseJournalEntry(transactionIdentifier);
      return transactionIdentifier;
    } else {
      return null;
    }
  }

  @Transactional
  public String deleteAccount(final String accountIdentifier) {
    final AccountEntity accountEntity = this.accountRepository.findByIdentifier(accountIdentifier);

    final List<CommandEntity> commandEntities = this.commandRepository.findByAccount(accountEntity);
    this.commandRepository.deleteAll(commandEntities);

    this.accountRepository.delete(accountEntity);
    return accountIdentifier;
  }

  @Transactional
  public void adjustLedgerTotals(final String ledgerIdentifier, final BigDecimal amount) {
    final LedgerEntity ledger = this.ledgerRepository.findByIdentifier(ledgerIdentifier);
    final BigDecimal currentTotal = ledger.getTotalValue() != null ? ledger.getTotalValue() : BigDecimal.ZERO;
    ledger.setTotalValue(currentTotal.add(amount));
    final LedgerEntity savedLedger = this.ledgerRepository.save(ledger);
    if (savedLedger.getParentLedger() != null) {
      this.adjustLedgerTotals(savedLedger.getParentLedger().getIdentifier(), amount);
    }
  }


  @Transactional
  public void releaseJournalEntry(final String transactionIdentifier) {
    final Optional<JournalEntryEntity> optionalJournalEntry = this.journalEntryRepository.findByTransactionIdentifier(transactionIdentifier);
    if (optionalJournalEntry.isPresent()) {
      final JournalEntryEntity journalEntryEntity = optionalJournalEntry.get();
      journalEntryEntity.setState(JournalEntry.State.PROCESSED.name());
      this.journalEntryRepository.save(journalEntryEntity);
    }
  }
}
