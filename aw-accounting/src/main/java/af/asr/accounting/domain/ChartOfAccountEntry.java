
package af.asr.accounting.domain;

@SuppressWarnings("unused")
public class ChartOfAccountEntry {

  private String code;
  private String name;
  private String description;
  private String type;
  private Integer level;

  public ChartOfAccountEntry() {
    super();
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public Integer getLevel() {
    return this.level;
  }

  public void setLevel(final Integer level) {
    this.level = level;
  }
}
