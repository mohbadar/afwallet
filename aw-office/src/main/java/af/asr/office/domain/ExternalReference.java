
package af.asr.office.domain;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;

import javax.validation.constraints.NotNull;

public class ExternalReference {

  public enum State {
    ACTIVE,
    INACTIVE
  }

  @ValidIdentifier
  private String type;
  @NotNull
  private State state;

  public ExternalReference() {
    super();
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getState() {
    return this.state.name();
  }

  public void setState(final String state) {
    this.state = State.valueOf(state);
  }
}
