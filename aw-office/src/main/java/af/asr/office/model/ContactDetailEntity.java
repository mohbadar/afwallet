
package af.asr.office.model;

import javax.persistence.*;

@Entity
@Table(name = "office_contact_details", schema = "office")
public class ContactDetailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "employee_id")
  private EmployeeEntity employee;
  @Column(name = "a_type")
  private String type;
  @Column(name = "a_group")
  private String group;
  @Column(name = "a_value")
  private String value;
  @Column(name = "preference_level")
  private Integer preferenceLevel;

  public ContactDetailEntity() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EmployeeEntity getEmployee() {
    return employee;
  }

  public void setEmployee(final EmployeeEntity employee) {
    this.employee = employee;
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getGroup() {
    return this.group;
  }

  public void setGroup(final String group) {
    this.group = group;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(final String value) {
    this.value = value;
  }

  public Integer getPreferenceLevel() {
    return this.preferenceLevel;
  }

  public void setPreferenceLevel(final Integer preferenceLevel) {
    this.preferenceLevel = preferenceLevel;
  }
}
