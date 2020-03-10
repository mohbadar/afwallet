
package af.gov.anar.query.infrastructure.common.api;

import java.util.Set;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ParameterListInclusionStrategy implements ExclusionStrategy {

    private final Set<String> parameterNamesToInclude;

    public ParameterListInclusionStrategy(final Set<String> parameterNamesToSkip) {
        this.parameterNamesToInclude = parameterNamesToSkip;
    }

    @Override
    public boolean shouldSkipField(final FieldAttributes f) {
        return !this.parameterNamesToInclude.contains(f.getName());
    }

    @SuppressWarnings("unused")
    @Override
    public boolean shouldSkipClass(final Class<?> clazz) {
        return false;
    }
}