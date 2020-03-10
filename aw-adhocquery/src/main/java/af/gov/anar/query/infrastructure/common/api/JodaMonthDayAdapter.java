
package af.gov.anar.query.infrastructure.common.api;

import java.lang.reflect.Type;

import org.joda.time.MonthDay;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serializer for joda time {@link MonthDay} that returns date in array format
 * to match previous jackson functionality.
 */
public class JodaMonthDayAdapter implements JsonSerializer<MonthDay> {

    @SuppressWarnings("unused")
    @Override
    public JsonElement serialize(final MonthDay src, final Type typeOfSrc, final JsonSerializationContext context) {

        JsonArray array = null;
        if (src != null) {
            array = new JsonArray();
            array.add(new JsonPrimitive(src.getMonthOfYear()));
            array.add(new JsonPrimitive(src.getDayOfMonth()));
        }

        return array;
    }
}