
package af.asr.payroll.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payroll_payroll_configurations", schema = "payroll")
public class PayrollConfigurationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "customer_identifier", nullable = false, length = 32)
  private String customerIdentifier;
  @Column(name = "main_account_number", nullable = false, length = 34)
  private String mainAccountNumber;
  @Column(name = "created_by")
  private String createdBy;
  @Column(name = "created_on")
//  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime createdOn;
  @Column(name = "last_modified_by")
  private String lastModifiedBy;
  @Column(name = "last_modified_on")
//  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime lastModifiedOn;

  public PayrollConfigurationEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getCustomerIdentifier() {
    return this.customerIdentifier;
  }

  public void setCustomerIdentifier(final String customerIdentifier) {
    this.customerIdentifier = customerIdentifier;
  }

  public String getMainAccountNumber() {
    return this.mainAccountNumber;
  }

  public void setMainAccountNumber(final String mainAccountNumber) {
    this.mainAccountNumber = mainAccountNumber;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(final String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(final LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public void setLastModifiedBy(final String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDateTime getLastModifiedOn() {
    return this.lastModifiedOn;
  }

  public void setLastModifiedOn(final LocalDateTime lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }
}
