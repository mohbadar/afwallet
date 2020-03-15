
package af.asr.office.mapper;

import af.asr.office.domain.Employee;
import af.asr.office.model.EmployeeEntity;

public class EmployeeMapper {

  private EmployeeMapper() {
    super();
  }

  public static EmployeeEntity map(final Employee employee) {
    final EmployeeEntity employeeEntity = new EmployeeEntity();
    employeeEntity.setIdentifier(employee.getIdentifier());
    employeeEntity.setGivenName(employee.getGivenName());
    employeeEntity.setMiddleName(employee.getMiddleName());
    employeeEntity.setSurname(employee.getSurname());
    return employeeEntity;
  }

  public static Employee map(final EmployeeEntity employeeEntity) {
    final Employee employee = new Employee();
    employee.setIdentifier(employeeEntity.getIdentifier());
    employee.setGivenName(employeeEntity.getGivenName());
    employee.setMiddleName(employeeEntity.getMiddleName());
    employee.setSurname(employeeEntity.getSurname());
    if (employeeEntity.getAssignedOffice() != null)
      employee.setAssignedOffice(employeeEntity.getAssignedOffice().getIdentifier());
    return employee;
  }
}
