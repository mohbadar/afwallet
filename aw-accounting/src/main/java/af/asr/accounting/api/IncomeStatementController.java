
package af.asr.accounting.api;


import af.asr.accounting.domain.financial.statement.IncomeStatement;
import af.asr.accounting.service.IncomeStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incomestatement")
public class IncomeStatementController {

  private final IncomeStatementService incomeStatementService;

  @Autowired
  public IncomeStatementController(final IncomeStatementService incomeStatementService) {
    super();
    this.incomeStatementService = incomeStatementService;
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_INCOME_STMT)
  @RequestMapping(
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  public ResponseEntity<IncomeStatement> getIncomeStatement() {
    return ResponseEntity.ok(this.incomeStatementService.getIncomeStatement());
  }
}
