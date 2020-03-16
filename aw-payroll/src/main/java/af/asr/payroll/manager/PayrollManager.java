
package af.asr.payroll.manager;

import af.asr.payroll.domain.PayrollCollectionHistory;
import af.asr.payroll.domain.PayrollCollectionSheet;
import af.asr.payroll.domain.PayrollConfiguration;
import af.asr.payroll.domain.PayrollPaymentPage;
import af.asr.payroll.exception.CustomerNotFoundException;
import af.asr.payroll.exception.PayrollConfigurationNotFoundException;
import af.asr.payroll.exception.PayrollDistributionValidationException;
import af.asr.payroll.exception.PayrollPaymentValidationException;
import java.util.List;
import javax.validation.Valid;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.util.CustomFeignClientsConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unused")
@FeignClient(name="payroll-v1", path="/payroll/v1", configuration = CustomFeignClientsConfiguration.class)
public interface PayrollManager {

  @RequestMapping(
      value = "/customers/{identifier}/payroll",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.NOT_FOUND, exception = CustomerNotFoundException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = PayrollDistributionValidationException.class)
  })
  void setPayrollConfiguration(@PathVariable(value = "identifier") final String customerIdentifier,
                               @RequestBody @Valid final PayrollConfiguration payrollConfiguration);

  @RequestMapping(
      value = "/customers/{identifier}/payroll",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.NOT_FOUND, exception = PayrollConfigurationNotFoundException.class)
  })
  PayrollConfiguration findPayrollConfiguration(@PathVariable(value = "identifier") final String customerIdentifier);

  @RequestMapping(
      value = "/distribution",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = PayrollPaymentValidationException.class),
      @ThrowsException(status = HttpStatus.CONFLICT, exception = PayrollPaymentValidationException.class)
  })
  void distribute(@RequestBody @Valid final PayrollCollectionSheet payrollCollectionSheet);

  @RequestMapping(
      value = "/distribution",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  List<PayrollCollectionHistory> fetchDistributionHistory();

  @RequestMapping(
      value = "/distribution/{identifier}/payments",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  PayrollPaymentPage fetchPayments(@PathVariable("identifier") final String identifier,
                                   @RequestParam(value = "pageIndex", required = false) final Integer pageIndex,
                                   @RequestParam(value = "size", required = false) final Integer size,
                                   @RequestParam(value = "sortColumn", required = false) final String sortColumn,
                                   @RequestParam(value = "sortDirection", required = false) final String sortDirection);

}
