
package af.asr.accounting.api;

import af.asr.accounting.domain.ChartOfAccountEntry;
import af.asr.accounting.service.ChartOfAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/chartofaccounts")
public class ChartOfAccountsController {

  private final ChartOfAccountsService chartOfAccountsService;

  @Autowired
  public ChartOfAccountsController(final ChartOfAccountsService chartOfAccountsService) {
    super();
    this.chartOfAccountsService = chartOfAccountsService;
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_LEDGER)
  @RequestMapping(
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  public ResponseEntity<List<ChartOfAccountEntry>> getChartOfAccounts() {
    return ResponseEntity.ok(this.chartOfAccountsService.getChartOfAccounts());
  }
}
