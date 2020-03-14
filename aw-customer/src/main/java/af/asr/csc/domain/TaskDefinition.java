
package af.asr.csc.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public final class TaskDefinition {

  public enum Type {
    ID_CARD,
    FOUR_EYES,
    CUSTOM
  }

  public enum Command {
    ACTIVATE,
    UNLOCK,
    REOPEN
  }

  @NotBlank
  private String identifier;
  @NotNull
  private Type type;
  @NotEmpty
  private Set<Command> commands;
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  private Boolean mandatory;
  private Boolean predefined;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  public String getType() {
    return this.type.name();
  }

  public void setType(final String type) {
    this.type = Type.valueOf(type);
  }

  public String[] getCommands() {
    return this.commands.stream().map(Enum::name).toArray(size -> new String[size]);
  }

  public void setCommands(final String... commandNames) {
    this.commands = new HashSet<>();
    for (String command : commandNames) {
      this.commands.add(Command.valueOf(command));
    }
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

  public Boolean getMandatory() {
    return this.mandatory;
  }

  public void setMandatory(final Boolean mandatory) {
    this.mandatory = mandatory;
  }

  public Boolean getPredefined() {
    return this.predefined;
  }

  public void setPredefined(final Boolean predefined) {
    this.predefined = predefined;
  }
}
