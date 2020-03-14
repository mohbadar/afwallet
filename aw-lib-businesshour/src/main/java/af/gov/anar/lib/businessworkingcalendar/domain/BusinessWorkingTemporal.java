package af.gov.anar.lib.businessworkingcalendar.domain;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * A temporal containing fields that can be used to define business hours. These
 * fields must be continguous and have a fixed range.
 */
public class BusinessWorkingTemporal implements Temporal, Comparable<Temporal> {

    private final NavigableMap<ChronoField, Integer> fieldValues;

    private BusinessWorkingTemporal(Map<ChronoField, Integer> fieldValues) {
        super();
        this.fieldValues = Collections.unmodifiableNavigableMap(new TreeMap<ChronoField, Integer>(fieldValues));
        validateFields(this.fieldValues.navigableKeySet());
    }

    /**
     * Builds a {@link BusinessWorkingTemporal} instance from its fields values
     *
     * @param fieldValues the values of the fields. The fields must be
     * contiguous and have a fixed range.
     * @return the {@link BusinessWorkingTemporal} instance
     */
    public static BusinessWorkingTemporal of(Map<ChronoField, Integer> fieldValues) {
        return new BusinessWorkingTemporal(fieldValues);
    }

    private static BusinessWorkingTemporal from(TemporalAccessor temporal, Set<ChronoField> supportedFields) {
        Map<ChronoField, Integer> fieldValues = new HashMap<>();
        supportedFields.forEach(field -> fieldValues.put(field, temporal.get(field)));
        return new BusinessWorkingTemporal(fieldValues);
    }

    /**
     * Check that the given fields are contiguous and have a fixed length.
     *
     * @param fields the fields
     */
    private static void validateFields(SortedSet<ChronoField> fields) {
        TemporalUnit expectedBaseUnit = fields.first().getBaseUnit();
        for (ChronoField field : fields) {
            if (!field.getBaseUnit().equals(expectedBaseUnit)) {
                throw new DateTimeException("the fields must be contiguous");
            }
            if (!field.range().isFixed()) {
                throw new DateTimeException("the fields must have a fixed range");
            }
            expectedBaseUnit = field.getRangeUnit();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R) fieldValues.firstKey().getBaseUnit();
        }
        return Temporal.super.query(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSupported(TemporalField field) {
        if (field instanceof ChronoField) {
            return fieldValues.keySet().contains(field);
        }
        return field.isSupportedBy(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(TemporalField field) {
        if (field instanceof ChronoField) {
            return Optional
                    .ofNullable(fieldValues.get(field))
                    .orElseThrow(() -> new UnsupportedTemporalTypeException("Unsupported field: " + field))
                    .longValue();
        }
        return field.getFrom(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return fieldValues.keySet()
                    .stream()
                    .map(TemporalField::getBaseUnit)
                    .anyMatch(unit::equals);
        }
        return unit.isSupportedBy(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Temporal with(TemporalField field, long newValue) {

        if (field instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) field;
            //copy the field values and replace with the new ones
            Map<ChronoField, Integer> newFieldValues = new HashMap<>(fieldValues);
            Optional
                    .ofNullable(newFieldValues.replace(chronoField, chronoField.checkValidIntValue(newValue)))
                    .orElseThrow(() -> new UnsupportedTemporalTypeException("Unsupported field: " + field));

            return new BusinessWorkingTemporal(newFieldValues);
        }
        return field.adjustInto(this, newValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Temporal plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            Map<ChronoField, Integer> newFieldValues = new HashMap<>(fieldValues);
            SortedSet<ChronoField> relevantFields = fieldsBiggerThan((ChronoUnit) unit);

            //add the given amount to the field, and cascade to the other fields if the new value is out of the possible range
            for (ChronoField field : relevantFields) {
                long rangeLength = field.range().getMaximum() - field.range().getMinimum() + 1;
                long sum = fieldValues.get(field) + amountToAdd - field.range().getMinimum();
                newFieldValues.put(field, field.checkValidIntValue(field.range().getMinimum() + Math.floorMod(sum, rangeLength)));
                amountToAdd = Math.floorDiv(sum, rangeLength);
            }
            return new BusinessWorkingTemporal(newFieldValues);
        }
        return unit.addTo(this, amountToAdd);
    }

    private Duration durationUntil(Temporal end) {
        TemporalUnit endPrecision = end.query(TemporalQueries.precision());
        return fieldValues
                .entrySet()
                .stream()
                .map(entry -> Duration.of(end.get(entry.getKey()) - entry.getValue(), entry.getKey().getBaseUnit()))
                .reduce(Duration.ZERO, Duration::plus)
                .plus(getLong(end, endPrecision, fieldValues.firstKey().getBaseUnit()), endPrecision);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        //the implementation requirements state that 'endExclusive' must first be converted into a BusinessWorkingTemporal
        //it means that some precision can be lost and the result can only be/precise up to the smallest supported
        //unit of this temporal
        BusinessWorkingTemporal end = from(endExclusive, fieldValues.keySet());
        if (unit instanceof ChronoUnit) {
            return durationInUnit(durationUntil(end), unit);
        }
        return unit.between(this, end);
    }

    /**
     * Increments the least significant field of this Business Temporal by one.
     *
     * @return a new Business Temporal with the incremented field
     */
    public BusinessWorkingTemporal increment() {
        return (BusinessWorkingTemporal) plus(1, fieldValues.firstKey().getBaseUnit());
    }

    /**
     * Get the amount of time between the provided temporal and the next
     * occurrence of this business temporal. Contrary to 'until', this method
     * returns a result of a precision equivalent to the precision of the
     * provided temporal, and guarantees that the result will be positive
     *
     * @param temporal the temporal, must support the "precision" query
     * @param unit the unit to measure the amount in
     * @return the time between the given temporal and this business temporal
     * (always positive)
     */
    public long since(final Temporal temporal, ChronoUnit unit) {
        Duration duration
                = durationUntil(temporal)
                .multipliedBy(-1); //because 'since' is actually the opposite of 'until'

        //if the result is negative, add the duration of the biggest supported field
        if (duration.isNegative()) {
            duration = duration.plus(fieldValues.lastKey().getRangeUnit().getDuration());
        }

        return durationInUnit(duration, unit);
    }

    /**
     * Get all the fields that are measured with a unit greater than or equals
     * to the provided unit.
     *
     * @param unit the unit
     * @return a set containing the relevant fields
     */
    private SortedSet<ChronoField> fieldsBiggerThan(ChronoUnit unit) {
        return fieldValues.tailMap(
                fieldValues
                        .keySet()
                        .stream()
                        .filter(f -> f.getBaseUnit().equals(unit))
                        .findAny()
                        .orElseThrow(() -> new UnsupportedTemporalTypeException("Unsupported unit: " + unit)),
                true)
                .navigableKeySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Temporal t) {
        return Long.signum(-until(t, fieldValues.firstKey().getBaseUnit()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return fieldValues.hashCode();
    }

    /**
     * Tells if this business temporal is equals to another one. Two business
     * temporals are equals if they support the same fields, and these fields
     * have the same values.
     * @param obj the object to comapare this business tempral to
     * @return true if this object and obj are equals, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return Optional.ofNullable(obj)
                .filter(BusinessWorkingTemporal.class::isInstance)
                .filter(other -> fieldValues.equals(((BusinessWorkingTemporal) other).fieldValues))
                .isPresent();
    }

    /**
     * Equivalent of Duration.get(unit), but supports units differents than
     * SECOND and NANO.
     *
     * @param duration the duration
     * @param unit the unit
     * @return the provided duration expressed in the provided unit
     */
    private static long durationInUnit(Duration duration, TemporalUnit unit) {
        return duration.toNanos() / unit.getDuration().toNanos();
    }

    /**
     * Equivalent of Temporal.getLong with arbitrary base and range units.
     *
     * @param temporal the temporal to query
     * @param baseUnit the base unit
     * @param rangeUnit the range unit
     * @return the amount of baseUnit in rangeUnit from the temporal
     */
    private static long getLong(Temporal temporal, TemporalUnit baseUnit, TemporalUnit rangeUnit) {

        long result = 0;
        long weight = 1;
        TemporalUnit currentUnit = baseUnit;

        //iterate through ChronoFields values (NANO_OF_SECOND -> SECOND_OF_MINUTE -> MINUTE_OF_HOUR etc.)
        //and sum the corresponding fields until we reach a unit bigger than baseUnit
        for (ChronoField field : ChronoField.values()) {
            if (field.getBaseUnit() == currentUnit && currentUnit.getDuration().compareTo(rangeUnit.getDuration()) < 0) {
                result += (temporal.getLong(field) - field.range().getMinimum()) * weight;
                weight *= field.range().getMaximum() - field.range().getMinimum() + 1;
                currentUnit = (ChronoUnit) field.getRangeUnit();
            }
        }
        return result % durationInUnit(rangeUnit.getDuration(), baseUnit);
    }

}