
package af.asr.csc.mapper;

import org.apache.fineract.cn.customer.catalog.api.v1.domain.Value;
import org.apache.fineract.cn.customer.catalog.internal.repository.FieldValueEntity;
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
