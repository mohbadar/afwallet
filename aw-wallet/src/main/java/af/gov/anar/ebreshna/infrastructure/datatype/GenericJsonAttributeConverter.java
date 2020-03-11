package af.gov.anar.ebreshna.infrastructure.datatype;

import java.io.IOException;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface GenericJsonAttributeConverter<X> extends AttributeConverter<X, String> {

    X getInstance();
    ObjectMapper getObjectMapper();

    @Override
    default String convertToDatabaseColumn(X attribute) {
        String jsonString = "";
        try {

            // conversion of POJO to json
            if(attribute != null) {
                jsonString = getObjectMapper().writeValueAsString(attribute);
            } else {
                jsonString = "{}"; // empty object to protect against NullPointerExceptions
            }
        } catch (JsonProcessingException ex) {
        }

        return jsonString;
    }

    @Override
    default X convertToEntityAttribute(String dbData) {
        X attribute = null;

        try {
            if(StringUtils.isNoneBlank(dbData)) {
                attribute = getObjectMapper().readValue(dbData, (Class<X>)getInstance().getClass());
            } else {
                attribute = getObjectMapper().readValue("{}", (Class<X>)getInstance().getClass());
            }
        } catch (IOException ex) {
        }

        return attribute;
    }

}
