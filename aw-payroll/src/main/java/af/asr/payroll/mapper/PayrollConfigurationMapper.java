
package af.asr.payroll.mapper;


import af.asr.payroll.domain.PayrollConfiguration;
import af.asr.payroll.model.PayrollConfigurationEntity;
import af.gov.anar.lang.validation.date.DateConverter;

public class PayrollConfigurationMapper {

  private PayrollConfigurationMapper() {
    super();
  }

  public static PayrollConfiguration map(final PayrollConfigurationEntity payrollConfigurationEntity) {
    final PayrollConfiguration payrollConfiguration = new PayrollConfiguration();
    payrollConfiguration.setMainAccountNumber(payrollConfigurationEntity.getMainAccountNumber());
    payrollConfiguration.setCreatedBy(payrollConfigurationEntity.getCreatedBy());
    payrollConfiguration.setCreatedOn(DateConverter.toIsoString(payrollConfigurationEntity.getCreatedOn()));
    if (payrollConfigurationEntity.getLastModifiedBy() != null) {
      payrollConfiguration.setLastModifiedBy(payrollConfigurationEntity.getLastModifiedBy());
      payrollConfiguration.setLastModifiedOn(DateConverter.toIsoString(payrollConfigurationEntity.getLastModifiedOn()));
    }
    return payrollConfiguration;
  }
}
