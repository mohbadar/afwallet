package af.gov.anar.lib.businessworkingcalendar.test.domain;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import af.gov.anar.lib.businessworkingcalendar.domain.BusinessWorkingPeriod;
import af.gov.anar.lib.businessworkingcalendar.domain.BusinessWorkingTemporal;
import org.junit.Test;

public class BusinessWorkingPeriodTest {

    @Test
    public void alwaysOpen() {
        assertTrue(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 0)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 59)))
                        .alwaysOpen());
        assertFalse(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 1)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 59)))
                        .alwaysOpen());
    }

    @Test
    public void isInPeriod() {

        assertTrue(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .isInPeriod(LocalTime.of(0, 10, 00)));

        assertTrue(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .isInPeriod(LocalTime.of(0, 15, 59)));

        assertFalse(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .isInPeriod(LocalTime.of(0, 16, 0)));

        assertFalse(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .isInPeriod(LocalTime.of(0, 9, 59)));
    }

    @Test
    public void timeBeforeOpening() {
        assertEquals(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .timeBeforeOpening(LocalTime.of(0, 9, 1), ChronoUnit.SECONDS), 59);

        assertEquals(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .timeBeforeOpening(LocalTime.of(0, 10, 0), ChronoUnit.SECONDS), 0);

        assertEquals(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .timeBeforeOpening(LocalTime.of(0, 10, 1), ChronoUnit.SECONDS), 3599);

        assertEquals(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 0)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 59)))
                        .timeBeforeOpening(LocalTime.of(0, 0), ChronoUnit.MINUTES), Long.MAX_VALUE);
    }

    @Test
    public void getStartCron() {
        assertEquals(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .getStartCron().toString(), "10 * * * *");

        assertNull(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 0)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 59)))
                        .getStartCron());
    }

    @Test
    public void getEndCron() {
        assertEquals(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15)))
                        .getEndCron().toString(), "16 * * * *");

        assertNull(
                new BusinessWorkingPeriod(
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 0)),
                        BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 59)))
                        .getEndCron());
    }

    @Test
    public void mergePeriods() {
        BusinessWorkingTemporal start1 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 10));
        BusinessWorkingTemporal end1 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 15));

        //the start of this period is included in the previous period
        BusinessWorkingTemporal start2 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 12));
        BusinessWorkingTemporal end2 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 20));

        //the start of this period is contiguous to the end of the previous period
        BusinessWorkingTemporal start3 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 21));
        BusinessWorkingTemporal end3 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 22));

        //this period is disjoint from the others
        BusinessWorkingTemporal start4 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 24));
        BusinessWorkingTemporal end4 = BusinessWorkingTemporal.of(Collections.singletonMap(ChronoField.MINUTE_OF_HOUR, 27));

        assertEquals(
                BusinessWorkingPeriod.merge(
                        Arrays.asList(
                                new BusinessWorkingPeriod(start1, end1),
                                new BusinessWorkingPeriod(start2, end2),
                                new BusinessWorkingPeriod(start3, end3),
                                new BusinessWorkingPeriod(start4, end4))),
                new HashSet<BusinessWorkingPeriod>() {
                    {
                        add(new BusinessWorkingPeriod(start1, end3));
                        add(new BusinessWorkingPeriod(start4, end4));
                    }
                }
        );
    }

}