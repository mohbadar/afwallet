
package af.asr.accounting.api;

import af.asr.accounting.domain.TransactionType;
import af.asr.accounting.domain.TransactionTypePage;
import af.asr.accounting.service.TransactionTypeService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/transactiontypes")
public class TransactionTypeRestController {

  private final TransactionTypeService transactionTypeService;

  @Autowired
  public TransactionTypeRestController(final TransactionTypeService transactionTypeService) {
    super();
    this.transactionTypeService = transactionTypeService;
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_TX_TYPES)
  @RequestMapping(
      value = "",
      method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE}
  )
  @ResponseBody
  ResponseEntity<Void> createTransactionType(@RequestBody @Valid final TransactionType transactionType) {
    if (this.transactionTypeService.findByIdentifier(transactionType.getCode()).isPresent()) {
      throw ServiceException.conflict("Transaction type '{0}' already exists.", transactionType.getCode());
    }

    this.transactionTypeService.createTransactionType(transactionType);
    return ResponseEntity.accepted().build();
  }


  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_TX_TYPES)
  @RequestMapping(
      value = "",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  ResponseEntity<TransactionTypePage> fetchTransactionTypes(@RequestParam(value = "term", required = false) final String term,
                                                            @RequestParam(value = "pageIndex", required = false) final Integer pageIndex,
                                                            @RequestParam(value = "size", required = false) final Integer size,
                                                            @RequestParam(value = "sortColumn", required = false) final String sortColumn,
                                                            @RequestParam(value = "sortDirection", required = false) final String sortDirection) {
    final String column2sort = "code".equalsIgnoreCase(sortColumn) ? "identifier" : sortColumn;
    return ResponseEntity.ok(
        this.transactionTypeService.fetchTransactionTypes(term,
            PageableBuilder.create(pageIndex, size, column2sort, sortDirection)));
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_TX_TYPES)
  @RequestMapping(
      value = "/{code}",
      method = RequestMethod.PUT,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE}
  )
  @ResponseBody
  ResponseEntity<Void> changeTransactionType(@PathVariable("code") final String code,
                                             @RequestBody @Valid final TransactionType transactionType) {
    if (!code.equals(transactionType.getCode())) {
      throw ServiceException.badRequest("Given transaction type {0} must match request path.", code);
    }

    if (!this.transactionTypeService.findByIdentifier(code).isPresent()) {
      throw ServiceException.notFound("Transaction type '{0}' not found.", code);
    }

    this.transactionTypeService.changeTransactionType(transactionType);

    return ResponseEntity.accepted().build();
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_TX_TYPES)
  @RequestMapping(
      value = "/{code}",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  ResponseEntity<TransactionType> findTransactionType(@PathVariable("code") final String code) {
    return ResponseEntity.ok(
        this.transactionTypeService.findByIdentifier(code)
            .orElseThrow(() -> ServiceException.notFound("Transaction type '{0}' not found.", code))
    );
  }
}
