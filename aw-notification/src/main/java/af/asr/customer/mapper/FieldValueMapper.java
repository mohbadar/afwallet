
package af.asr.customer.mapper;

import af.asr.catalog.domain.Value;
import af.asr.catalog.model.FieldValueEntity;
import org.springframework.stereotype.Component;

@Component
public class FieldValueMapper {

  private FieldValueMapper() {
    super();
  }

  public static FieldValueEntity map(final Value value) {
    final FieldValueEntity fieldValueEntity = new FieldValueEntity();
    fieldValueEntity.setValue(value.getValue());
    return fieldValueEntity;
  }
}
