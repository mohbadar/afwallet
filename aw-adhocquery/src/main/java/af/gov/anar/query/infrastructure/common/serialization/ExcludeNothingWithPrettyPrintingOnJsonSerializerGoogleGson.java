
package af.gov.anar.query.infrastructure.common.serialization;

import af.gov.anar.query.infrastructure.common.api.JodaDateTimeAdapter;
import af.gov.anar.query.infrastructure.common.api.JodaLocalDateAdapter;
import af.gov.anar.query.infrastructure.common.api.JodaMonthDayAdapter;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.MonthDay;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <p>
 * A google gson implementation of 
 * contract.
 * </p>
 * 
 * <p>
 * It serializes all fields of any Java {@link Object} passed to it.
 * </p>
 */
@Component
public final class ExcludeNothingWithPrettyPrintingOnJsonSerializerGoogleGson {

    private final Gson gson;

    public ExcludeNothingWithPrettyPrintingOnJsonSerializerGoogleGson() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new JodaLocalDateAdapter());
        builder.registerTypeAdapter(DateTime.class, new JodaDateTimeAdapter());
        builder.registerTypeAdapter(MonthDay.class, new JodaMonthDayAdapter());
        builder.setPrettyPrinting();

        this.gson = builder.create();
    }

    public String serialize(final Object result) {
        return this.gson.toJson(result);
    }
}