
package af.asr.catalog.mapper;

import org.apache.fineract.cn.api.util.UserContextHolder;
import org.apache.fineract.cn.customer.catalog.api.v1.domain.Option;
import org.apache.fineract.cn.customer.catalog.internal.repository.FieldEntity;
import org.apache.fineract.cn.customer.catalog.internal.repository.OptionEntity;
import org.apache.fineract.cn.lang.DateConverter;

import java.time.Clock;
import java.time.LocalDateTime;

public class OptionMapper {

  private OptionMapper() {
    super();
  }

  public static OptionEntity map(final FieldEntity fieldEntity, final Option option) {
    final OptionEntity optionEntity = new OptionEntity();
    optionEntity.setField(fieldEntity);
    optionEntity.setLabel(option.getLabel());
    optionEntity.setValue(option.getValue());
    optionEntity.setCreatedBy(UserContextHolder.checkedGetUser());
    optionEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
    return optionEntity;
  }

  public static Option map(final OptionEntity optionEntity) {
    final Option option = new Option();
    option.setLabel(optionEntity.getLabel());
    option.setValue(optionEntity.getValue());
    option.setCreatedBy(optionEntity.getCreatedBy());
    option.setCreatedOn(DateConverter.toIsoString(optionEntity.getCreatedOn()));
    return option;
  }
}
