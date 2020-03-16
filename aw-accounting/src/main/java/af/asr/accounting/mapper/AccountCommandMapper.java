
package af.asr.accounting.mapper;


import af.asr.accounting.domain.AccountCommand;
import af.asr.accounting.model.CommandEntity;
import af.gov.anar.lang.validation.date.DateConverter;

public class AccountCommandMapper {

  public AccountCommandMapper() {
    super();
  }

  public static AccountCommand map(final CommandEntity commandEntity){
    final AccountCommand command = new AccountCommand();
    command.setAction(commandEntity.getType());
    command.setComment(commandEntity.getComment());
    command.setCreatedBy(commandEntity.getCreatedBy());
    command.setCreatedOn(DateConverter.toIsoString(commandEntity.getCreatedOn()));
    return command;
  }
}
