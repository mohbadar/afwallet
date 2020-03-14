
package af.asr.csc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

  public String[] getCommands() {
    return this.commands.stream().map(Enum::name).toArray(size -> new String[size]);
  }

  public void setCommands(final String... commandNames) throws IllegalArgumentException {
    this.commands = new HashSet<>();
    for (String command : commandNames) {
//     this.commands.add(Command.valueOf(command));
    }
  }

}
