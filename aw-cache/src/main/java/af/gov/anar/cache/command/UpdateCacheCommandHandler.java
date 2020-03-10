
package af.gov.anar.cache.command;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import af.gov.anar.cache.annotation.CommandType;
import af.gov.anar.cache.domain.CacheType;
import af.gov.anar.cache.service.CacheWritePlatformService;
import af.gov.anar.cache.util.CacheApiConstants;
import af.gov.anar.lang.data.ApiParameterError;
import af.gov.anar.lang.data.CommandProcessingResult;
import af.gov.anar.lang.data.CommandProcessingResultBuilder;
import af.gov.anar.lang.infrastructure.exception.common.InvalidJsonException;
import af.gov.anar.lang.infrastructure.exception.common.PlatformApiDataValidationException;
import af.gov.anar.lang.validation.DataValidatorBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;

@Service
@CommandType(entity = "CACHE", action = "UPDATE")
public class UpdateCacheCommandHandler {

    private final CacheWritePlatformService cacheService;
    private static final Set<String> REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(CacheApiConstants
            .cacheTypeParameter));

    @Autowired
    public UpdateCacheCommandHandler(final CacheWritePlatformService cacheService) {
        this.cacheService = cacheService;
    }

    @Transactional
    public CommandProcessingResult processCommand(final JsonCommand command) {

        final String json = command.json();

        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        command.checkForUnsupportedParameters(typeOfMap, json, REQUEST_DATA_PARAMETERS);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
                .resource(CacheApiConstants.RESOURCE_NAME.toLowerCase());

        final int cacheTypeEnum = command.integerValueSansLocaleOfParameterNamed(CacheApiConstants.cacheTypeParameter);
        baseDataValidator.reset().parameter(CacheApiConstants.cacheTypeParameter).value(Integer.valueOf(cacheTypeEnum)).notNull()
                .isOneOfTheseValues(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));

        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException(dataValidationErrors); }

        final CacheType cacheType = CacheType.fromInt(cacheTypeEnum);

        final Map<String, Object> changes = this.cacheService.switchToCache(cacheType);

        return new CommandProcessingResultBuilder().withCommandId(command.commandId()).with(changes).build();
    }
}