
package af.asr.accounting.api;

import af.asr.accounting.domain.Account;
import af.asr.accounting.domain.JournalEntry;
import af.asr.accounting.service.AccountService;
import af.asr.accounting.service.JournalEntryService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import af.gov.anar.lang.validation.date.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused"})
@RestController
@RequestMapping("/journal")
public class JournalRestController {

  private final JournalEntryService journalEntryService;
  private final AccountService accountService;

  @Autowired
  public JournalRestController(final JournalEntryService journalEntryService,
                               final AccountService accountService) {
    super();
    this.journalEntryService = journalEntryService;
    this.accountService = accountService;
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_JOURNAL)
  @RequestMapping(
      method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE}
  )
  @ResponseBody
  ResponseEntity<Void> createJournalEntry(@RequestBody @Valid final JournalEntry journalEntry) {
    if (this.journalEntryService.findJournalEntry(journalEntry.getTransactionIdentifier()).isPresent()) {
      throw ServiceException.conflict("Journal entry {0} already exists.", journalEntry.getTransactionIdentifier());
    }

    if (journalEntry.getDebtors().size() == 0) {
      throw ServiceException.badRequest("Debtors must be given.");
    }
    if (journalEntry.getCreditors().size() == 0) {
      throw ServiceException.badRequest("Creditors must be given.");
    }

    final Double debtorAmountSum = journalEntry.getDebtors()
        .stream()
        .peek(debtor -> {
          final Optional<Account> accountOptional = this.accountService.findAccount(debtor.getAccountNumber());
          if (!accountOptional.isPresent()) {
            throw ServiceException.badRequest("Unknown debtor account{0}.", debtor.getAccountNumber());
          }
          if (!accountOptional.get().getState().equals(Account.State.OPEN.name())) {
            throw ServiceException.conflict("Debtor account{0} must be in state open.", debtor.getAccountNumber());
          }
        })
        .map(debtor -> Double.valueOf(debtor.getAmount()))
        .reduce(0.0D, (x, y) -> x + y);

    final Double creditorAmountSum = journalEntry.getCreditors()
        .stream()
        .peek(creditor -> {
          final Optional<Account> accountOptional = this.accountService.findAccount(creditor.getAccountNumber());
          if (!accountOptional.isPresent()) {
            throw ServiceException.badRequest("Unknown creditor account{0}.", creditor.getAccountNumber());
          }
          if (!accountOptional.get().getState().equals(Account.State.OPEN.name())) {
            throw ServiceException.conflict("Creditor account{0} must be in state open.", creditor.getAccountNumber());
          }
        })
        .map(creditor -> Double.valueOf(creditor.getAmount()))
        .reduce(0.0D, (x, y) -> x + y);

    if (!debtorAmountSum.equals(creditorAmountSum)) {
      throw ServiceException.conflict(
          "Sum of debtor and sum of creditor amounts must be equals.");
    }

    this.journalEntryService.createJournalEntry(journalEntry);
    return ResponseEntity.accepted().build();
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_JOURNAL)
  @RequestMapping(
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  ResponseEntity<List<JournalEntry>> fetchJournalEntries(
      @RequestParam(value = "dateRange", required = false) final String dateRange,
      @RequestParam(value = "account", required = false) final String accountNumber,
      @RequestParam(value = "amount", required = false) final BigDecimal amount
  ) {
    final DateRange range = DateRange.fromIsoString(dateRange);

    return ResponseEntity.ok(this.journalEntryService.fetchJournalEntries(range, accountNumber, amount));
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_JOURNAL)
  @RequestMapping(
      value = "/{transactionIdentifier}",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  ResponseEntity<JournalEntry> findJournalEntry(
      @PathVariable("transactionIdentifier") final String transactionIdentifier
  ) {
    final Optional<JournalEntry> optionalJournalEntry =
        this.journalEntryService.findJournalEntry(transactionIdentifier);

    if (optionalJournalEntry.isPresent()) {
      return ResponseEntity.ok(optionalJournalEntry.get());
    } else {
      throw ServiceException.notFound("Journal entry {0} not found.", transactionIdentifier);
    }
  }
}
