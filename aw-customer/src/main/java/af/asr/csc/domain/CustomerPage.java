
package af.asr.csc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPage {

  private List<Customer> customers;
  private Integer totalPages;
  private Long totalElements;

}
