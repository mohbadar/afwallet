
package af.asr.infrastructure.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.apache.fineract.cn.lang.DateConverter;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

  public LocalDateTimeConverter() {
    super();
  }

  @Override
  public Timestamp convertToDatabaseColumn(final LocalDateTime attribute) {
    if (attribute == null) {
      return null;
    } else {
      return new Timestamp(DateConverter.toEpochMillis(attribute));
    }
  }

  @Override
  public LocalDateTime convertToEntityAttribute(final Timestamp dbData) {
    if (dbData == null) {
      return null;
    } else {
      return DateConverter.fromEpochMillis(dbData.getTime());
    }
  }
}
