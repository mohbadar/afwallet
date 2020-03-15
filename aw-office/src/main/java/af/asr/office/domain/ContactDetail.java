
package af.asr.office.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ContactDetail {

  @NotNull
  private Type type;
  @NotNull
  private Group group;
  @NotBlank
  private String value;
  @Min(1)
  @Max(127)
  private Integer preferenceLevel;
  public ContactDetail() {
    super();
  }

  public String getType() {
    return this.type.name();
  }

  public void setType(final String type) {
    this.type = Type.valueOf(type);
  }

  public String getGroup() {
    return this.group.name();
  }

  public void setGroup(final String group) {
    this.group = Group.valueOf(group);
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

  public enum Type {
    EMAIL,
    PHONE,
    MOBILE
  }

  public enum Group {
    BUSINESS,
    PRIVATE
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactDetail that = (ContactDetail) o;
    return type == that.type &&
            group == that.group &&
            Objects.equals(value, that.value) &&
            Objects.equals(preferenceLevel, that.preferenceLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, group, value, preferenceLevel);
  }

  @Override
  public String toString() {
    return "ContactDetail{" +
            "type=" + type +
            ", group=" + group +
            ", value='" + value + '\'' +
            ", preferenceLevel=" + preferenceLevel +
            '}';
  }
}
