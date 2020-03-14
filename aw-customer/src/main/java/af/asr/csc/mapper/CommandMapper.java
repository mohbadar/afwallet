
package af.asr.csc.mapper;

import af.asr.csc.domain.*;
import af.asr.csc.model.CommandEntity;
import af.asr.csc.model.CustomerEntity;
import af.asr.infrastructure.service.UserService;
import af.gov.anar.lang.validation.date.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public final class CommandMapper {


  private static UserService userService;

  @Autowired
  private CommandMapper(UserService userService) {
    this.userService = userService;
  }

  public static CommandEntity create(final CustomerEntity customer, final String action, final String comment) {
    final CommandEntity commandEntity = new CommandEntity();
    commandEntity.setCustomer(customer);
    commandEntity.setType(action);
    commandEntity.setComment(comment);
    commandEntity.setCreatedBy(userService.getPreferredUsername());
    commandEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
    return commandEntity;
  }

  public static Command map(final CommandEntity commandEntity) {
    final Command command = new Command();
    command.setAction(commandEntity.getType());
    command.setComment(commandEntity.getComment());
    command.setCreatedBy(commandEntity.getCreatedBy());
    command.setCreatedOn(DateConverter.toIsoString(commandEntity.getCreatedOn()));
    return command;
  }
}
