
package af.gov.anar.query.adhocquery.api;

import java.util.HashSet;
import java.util.Set;

/***
 * Enum of all parameters passed in while creating/updating a AdHocQuery 
 ***/
public enum AdHocJsonInputParams {
    ID("id"), NAME("name"),QUERY("query"),TABLENAME("tableName"), TABLEFIELDS("tableFields"), ISACTIVE("isActive"),
    REPORT_RUN_FREQUENCY("reportRunFrequency"),
    REPORT_RUN_EVERY("reportRunEvery"),
    EMAIL("email");

    private final String value;

    private AdHocJsonInputParams(final String value) {
        this.value = value;
    }

    private static final Set<String> values = new HashSet<>();
    static {
        for (final AdHocJsonInputParams type : AdHocJsonInputParams.values()) {
            values.add(type.value);
        }
    }

    public static Set<String> getAllValues() {
        return values;
    }

    @Override
    public String toString() {
        return name().toString().replaceAll("_", " ");
    }

    public String getValue() {
        return this.value;
    }
}