
package af.gov.anar.query.adhocquery.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ReportRunFrequency {
    DAILY(1, "reportRunFrequency.daily"),
    WEEKLY(2, "reportRunFrequency.weekly"),
    MONTHLY(3, "reportRunFrequency.monthly"),
    YEARLY(4, "reportRunFrequency.yearly"),
    CUSTOM(5, "reportRunFrequency.custom");

    private static final Map<Long, ReportRunFrequency> MAP = Arrays.stream(ReportRunFrequency.values()).collect(Collectors.toMap(
        ReportRunFrequency::getValue, e -> e
    ));

    private final long value;
    private final String code;

    private ReportRunFrequency(final long value, final String code) {
        this.value = value;
        this.code = code;
    }

    public long getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    public static ReportRunFrequency fromId(final long id) {
        return ReportRunFrequency.MAP.get(id);
    }
}
