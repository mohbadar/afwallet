
package af.asr.csc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpirationDate {

  @NotNull
  @Min(1000L)
  @Max(9999L)
  private Integer year;
  @NotNull
  @Min(1L)
  @Max(12L)
  private Integer month;
  @NotNull
  @Min(1L)
  @Max(31L)
  private Integer day;

  public static ExpirationDate fromLocalDate(LocalDate localDate) {
    ExpirationDate dateOfBirth = new ExpirationDate();
    dateOfBirth.setYear(localDate.getYear());
    dateOfBirth.setMonth(localDate.getMonthValue());
    dateOfBirth.setDay(localDate.getDayOfMonth());
    return dateOfBirth;
  }
}
