
package af.asr.accounting.api;

import af.asr.accounting.domain.financial.statement.FinancialCondition;
import af.asr.accounting.service.FinancialConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financialcondition")
public class FinancialConditionController {

  private final FinancialConditionService financialConditionService;

  @Autowired
  public FinancialConditionController(final FinancialConditionService financialConditionService) {
    super();
    this.financialConditionService = financialConditionService;
  }

  //@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.THOTH_FIN_CONDITION)
  @RequestMapping(
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.ALL_VALUE}
  )
  @ResponseBody
  public ResponseEntity<FinancialCondition> getFinancialCondition() {
    return ResponseEntity.ok(this.financialConditionService.getFinancialCondition());
  }
}
