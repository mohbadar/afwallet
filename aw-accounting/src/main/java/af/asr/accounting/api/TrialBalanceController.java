
package af.asr.accounting.api;

import af.asr.accounting.domain.financial.statement.TrialBalance;
import af.asr.accounting.service.TrialBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/trialbalance")
public class TrialBalanceController {

  private final TrialBalanceService trialBalanceService;

  @Autowired
  public TrialBalanceController(final TrialBalanceService trialBalanceService) {
    super();
    this.trialBalanceService = trialBalanceService;
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_LEDGER)
  @RequestMapping(
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  public ResponseEntity<TrialBalance> getTrialBalance(
      @RequestParam(value = "includeEmptyEntries", required = false) final boolean includeEmptyEntries) {
    return ResponseEntity.ok(this.trialBalanceService.getTrialBalance(includeEmptyEntries));
  }
}
