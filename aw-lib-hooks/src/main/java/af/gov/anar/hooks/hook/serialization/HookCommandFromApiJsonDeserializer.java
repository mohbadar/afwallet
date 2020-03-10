
package af.gov.anar.hooks.hook.serialization;

import af.gov.anar.hooks.hook.api.HookApiConstants;
import af.gov.anar.hooks.infrastructure.common.command.FromJsonHelper;
import af.gov.anar.lang.data.ApiParameterError;
import af.gov.anar.lang.infrastructure.exception.common.InvalidJsonException;
import af.gov.anar.lang.infrastructure.exception.common.PlatformApiDataValidationException;
import af.gov.anar.lang.validation.DataValidatorBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.*;

@Component
public class HookCommandFromApiJsonDeserializer {

    /**
     * The parameters supported for this command.
     */
    private final Set<String> supportedParameters = new HashSet<>(
            Arrays.asList("name", "displayName", "isActive", "events",
                    "config", "templateId"));
    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public HookCommandFromApiJsonDeserializer(
            final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new InvalidJsonException();
        }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {
        }.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json,
                this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(
                dataValidationErrors).resource("hook");

        final JsonElement element = this.fromApiJsonHelper.parse(json);

        final String name = this.fromApiJsonHelper.extractStringNamed("name",
                element);
        baseDataValidator.reset().parameter("name").value(name).notBlank()
                .notExceedingLengthOf(100);

        if (this.fromApiJsonHelper.parameterExists(
                HookApiConstants.templateIdParamName, element)) {
            final Long templateId = this.fromApiJsonHelper.extractLongNamed(
                    HookApiConstants.templateIdParamName, element);
            baseDataValidator.reset()
                    .parameter(HookApiConstants.templateIdParamName)
                    .value(templateId).notNull().integerGreaterThanZero();
        }

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    public void validateForUpdate(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new InvalidJsonException();
        }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {
        }.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json,
                this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(
                dataValidationErrors).resource("hook");

        final JsonElement element = this.fromApiJsonHelper.parse(json);
        if (this.fromApiJsonHelper.parameterExists("name", element)) {
            final String name = this.fromApiJsonHelper.extractStringNamed(
                    "name", element);
            baseDataValidator.reset().parameter("name").value(name).notBlank()
                    .notExceedingLengthOf(100);
        }

        if (this.fromApiJsonHelper.parameterExists(
                HookApiConstants.templateIdParamName, element)) {
            final Long templateId = this.fromApiJsonHelper.extractLongNamed(
                    HookApiConstants.templateIdParamName, element);
            baseDataValidator.reset()
                    .parameter(HookApiConstants.templateIdParamName)
                    .value(templateId).notNull().integerGreaterThanZero();
        }

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    private void throwExceptionIfValidationWarningsExist(
            final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) {
            throw new PlatformApiDataValidationException(
                    "validation.msg.validation.errors.exist",
                    "Validation errors exist.", dataValidationErrors);
        }
    }

}
