
package af.asr.office.domain;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
public class EmployeePage {

  @Valid
  private List<Employee> employees;
  private Integer totalPages;
  private Long totalElements;

  public EmployeePage() {
    super();
  }

  public List<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(final List<Employee> employees) {
    this.employees = employees;
  }

  public Integer getTotalPages() {
    return this.totalPages;
  }

  public void setTotalPages(final Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Long getTotalElements() {
    return this.totalElements;
  }

  public void setTotalElements(final Long totalElements) {
    this.totalElements = totalElements;
  }
}
