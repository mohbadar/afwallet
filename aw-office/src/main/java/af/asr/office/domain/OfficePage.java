
package af.asr.office.domain;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
public class OfficePage {

  @Valid
  private List<Office> offices;
  private Integer totalPages;
  private Long totalElements;

  public OfficePage() {
    super();
  }

  public List<Office> getOffices() {
    return this.offices;
  }

  public void setOffices(final List<Office> offices) {
    this.offices = offices;
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
