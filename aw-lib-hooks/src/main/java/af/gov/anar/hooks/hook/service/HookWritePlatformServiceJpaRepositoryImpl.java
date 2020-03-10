
package af.gov.anar.hooks.hook.service;

import static af.gov.anar.hooks.hook.api.HookApiConstants.actionNameParamName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.configParamName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.contentTypeName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.entityNameParamName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.eventsParamName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.nameParamName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.payloadURLName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.templateIdParamName;
import static af.gov.anar.hooks.hook.api.HookApiConstants.webTemplateName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.PersistenceException;

import af.gov.anar.hooks.infrastructure.common.command.FromJsonHelper;
import af.gov.anar.hooks.infrastructure.common.command.JsonCommand;
import af.gov.anar.hooks.template.domain.Template;
import af.gov.anar.hooks.template.domain.TemplateRepository;
import af.gov.anar.hooks.template.exception.TemplateNotFoundException;
import af.gov.anar.lang.data.ApiParameterError;
import af.gov.anar.lang.data.CommandProcessingResult;
import af.gov.anar.lang.data.CommandProcessingResultBuilder;
import af.gov.anar.lang.infrastructure.exception.common.PlatformApiDataValidationException;
import af.gov.anar.lang.infrastructure.exception.common.PlatformDataIntegrityException;
import af.gov.anar.lang.validation.DataValidatorBuilder;
import org.apache.commons.lang.exception.ExceptionUtils;
import af.gov.anar.hooks.hook.domain.Hook;
import af.gov.anar.hooks.hook.domain.HookConfiguration;
import af.gov.anar.hooks.hook.domain.HookRepository;
import af.gov.anar.hooks.hook.domain.HookResource;
import af.gov.anar.hooks.hook.domain.HookTemplate;
import af.gov.anar.hooks.hook.domain.HookTemplateRepository;
import af.gov.anar.hooks.hook.domain.Schema;
import af.gov.anar.hooks.hook.exception.HookNotFoundException;
import af.gov.anar.hooks.hook.exception.HookTemplateNotFoundException;
import af.gov.anar.hooks.hook.processor.ProcessorHelper;
import af.gov.anar.hooks.hook.processor.WebHookService;
import af.gov.anar.hooks.hook.serialization.HookCommandFromApiJsonDeserializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.RetrofitError;

@Service
public class HookWritePlatformServiceJpaRepositoryImpl
        implements
            HookWritePlatformService {

    private final HookRepository hookRepository;
    private final HookTemplateRepository hookTemplateRepository;
    private final TemplateRepository ugdTemplateRepository;
    private final HookCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public HookWritePlatformServiceJpaRepositoryImpl(
            final HookRepository hookRepository,
            final HookTemplateRepository hookTemplateRepository,
            final TemplateRepository ugdTemplateRepository,
            final HookCommandFromApiJsonDeserializer fromApiJsonDeserializer,
            final FromJsonHelper fromApiJsonHelper) {
        this.hookRepository = hookRepository;
        this.hookTemplateRepository = hookTemplateRepository;
        this.ugdTemplateRepository = ugdTemplateRepository;
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    @Transactional
    @Override
    @CacheEvict(value = "hooks", allEntries = true)
    public CommandProcessingResult createHook(final JsonCommand command) {

        try {

            this.fromApiJsonDeserializer.validateForCreate(command.json());

            final HookTemplate template = retrieveHookTemplateBy(command
                    .stringValueOfParameterNamed(nameParamName));
            final String configJson = command.jsonFragment(configParamName);
            final Set<HookConfiguration> config = assembleConfig(
                    command.mapValueOfParameterNamed(configJson), template);
            final JsonArray events = command
                    .arrayOfParameterNamed(eventsParamName);
            final Set<HookResource> allEvents = assembleSetOfEvents(events);
            Template ugdTemplate = null;
            if (command.hasParameter(templateIdParamName)) {
                final Long ugdTemplateId = command
                        .longValueOfParameterNamed(templateIdParamName);
                ugdTemplate = this.ugdTemplateRepository.getOne(ugdTemplateId);
                if (ugdTemplate == null) {
                    throw new TemplateNotFoundException(ugdTemplateId);
                }
            }
            final Hook hook = Hook.fromJson(command, template, config,
                    allEvents, ugdTemplate);

            validateHookRules(template, config, allEvents);

            this.hookRepository.save(hook);

            return new CommandProcessingResultBuilder()
                    .withCommandId(command.commandId())
                    .withEntityId(hook.getId()).build();
        } catch (final DataIntegrityViolationException dve) {
            handleHookDataIntegrityIssues(command, dve.getMostSpecificCause(), dve);
            return CommandProcessingResult.empty();
        }catch (final PersistenceException dve) {
        	Throwable throwable = ExceptionUtils.getRootCause(dve.getCause()) ;
        	handleHookDataIntegrityIssues(command, throwable, dve);
        	return CommandProcessingResult.empty();
        }
    }

    @Transactional
    @Override
    @CacheEvict(value = "hooks", allEntries = true)
    public CommandProcessingResult updateHook(final Long hookId,
            final JsonCommand command) {

        try {

            this.fromApiJsonDeserializer.validateForUpdate(command.json());

            final Hook hook = retrieveHookBy(hookId);
            final HookTemplate template = hook.getHookTemplate();
            final Map<String, Object> changes = hook.update(command);

            if (!changes.isEmpty()) {

                if (changes.containsKey(templateIdParamName)) {
                    final Long ugdTemplateId = command
                            .longValueOfParameterNamed(templateIdParamName);
                    final Template ugdTemplate = this.ugdTemplateRepository
                            .getOne(ugdTemplateId);
                    if (ugdTemplate == null) {
                        changes.remove(templateIdParamName);
                        throw new HookTemplateNotFoundException(ugdTemplateId);
                    }
                    hook.updateUgdTemplate(ugdTemplate);
                }

                if (changes.containsKey(eventsParamName)) {
                    final Set<HookResource> events = assembleSetOfEvents(command
                            .arrayOfParameterNamed(eventsParamName));
                    final boolean updated = hook.updateEvents(events);
                    if (!updated) {
                        changes.remove(eventsParamName);
                    }
                }

                if (changes.containsKey(configParamName)) {
                    final String configJson = command
                            .jsonFragment(configParamName);
                    final Set<HookConfiguration> config = assembleConfig(
                            command.mapValueOfParameterNamed(configJson),
                            template);
                    final boolean updated = hook.updateConfig(config);
                    if (!updated) {
                        changes.remove(configParamName);
                    }
                }

                this.hookRepository.saveAndFlush(hook);
            }

            return new CommandProcessingResultBuilder() //
                    .withCommandId(command.commandId()) //
                    .withEntityId(hookId) //
                    .with(changes) //
                    .build();
        } catch (final DataIntegrityViolationException dve) {
            handleHookDataIntegrityIssues(command, dve.getMostSpecificCause(), dve);
            return CommandProcessingResult.empty();
        }catch (final PersistenceException dve) {
        	Throwable throwable = ExceptionUtils.getRootCause(dve.getCause()) ;
        	handleHookDataIntegrityIssues(command, throwable, dve);
        	return CommandProcessingResult.empty();
        }
    }

    @Transactional
    @Override
    @CacheEvict(value = "hooks", allEntries = true)
    public CommandProcessingResult deleteHook(final Long hookId) {

        final Hook hook = retrieveHookBy(hookId);
        try {
            this.hookRepository.delete(hook);
        } catch (final DataIntegrityViolationException e) {
            throw new PlatformDataIntegrityException(
                    "error.msg.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource: "
                            + e.getMostSpecificCause());
        }
        return new CommandProcessingResultBuilder().withEntityId(hookId)
                .build();
    }

    private Hook retrieveHookBy(final Long hookId) {
        final Hook hook = this.hookRepository.getOne(hookId);
        if (hook == null) {
            throw new HookNotFoundException(hookId);
        }
        return hook;
    }

    private HookTemplate retrieveHookTemplateBy(final String templateName) {
        final HookTemplate template = this.hookTemplateRepository
                .findOne(templateName);
        if (template == null) {
            throw new HookTemplateNotFoundException(templateName);
        }
        return template;
    }

    private Set<HookConfiguration> assembleConfig(
            final Map<String, String> hookConfig, final HookTemplate template) {

        final Set<HookConfiguration> configuration = new HashSet<>();
        final Set<Schema> fields = template.getSchema();

        for (final Entry<String, String> configEntry : hookConfig.entrySet()) {
            for (final Schema field : fields) {
                final String fieldName = field.getFieldName();
                if (fieldName.equalsIgnoreCase(configEntry.getKey())) {

                    final HookConfiguration config = HookConfiguration
                            .createNewWithoutHook(field.getFieldType(),
                                    configEntry.getKey(),
                                    configEntry.getValue());
                    configuration.add(config);
                    break;
                }
            }

        }

        return configuration;
    }

    private Set<HookResource> assembleSetOfEvents(final JsonArray eventsArray) {

        final Set<HookResource> allEvents = new HashSet<>();

        for (int i = 0; i < eventsArray.size(); i++) {

            final JsonObject eventElement = eventsArray.get(i)
                    .getAsJsonObject();

            final String entityName = this.fromApiJsonHelper
                    .extractStringNamed(entityNameParamName, eventElement);
            final String actionName = this.fromApiJsonHelper
                    .extractStringNamed(actionNameParamName, eventElement);
            final HookResource event = HookResource.createNewWithoutHook(
                    entityName, actionName);
            allEvents.add(event);
        }

        return allEvents;
    }

    private void validateHookRules(final HookTemplate template,
            final Set<HookConfiguration> config, Set<HookResource> events) {

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(
                dataValidationErrors).resource("hook");

        if (!template.getName().equalsIgnoreCase(webTemplateName)
                && this.hookRepository.findOneByTemplateId(template.getId()) != null) {
            final String errorMessage = "multiple.non.web.template.hooks.not.supported";
            baseDataValidator.reset().failWithCodeNoParameterAddedToErrorCode(
                    errorMessage);
        }

        for (final HookConfiguration conf : config) {
            final String fieldValue = conf.getFieldValue();
            if (conf.getFieldName().equals(contentTypeName)) {
                if (!(fieldValue.equalsIgnoreCase("json") || fieldValue
                        .equalsIgnoreCase("form"))) {
                    final String errorMessage = "content.type.must.be.json.or.form";
                    baseDataValidator.reset()
                            .failWithCodeNoParameterAddedToErrorCode(
                                    errorMessage);
                }
            }

            if (conf.getFieldName().equals(payloadURLName)) {
                try {
                    final WebHookService service = ProcessorHelper
                            .createWebHookService(fieldValue);
                    service.sendEmptyRequest();
                } catch (RetrofitError re) {
                    // Swallow error if it's because of method not supported or
                    // if url throws 404 - required for integration test,
                    // url generated on 1st POST request
                    if (re.getResponse() == null) {
                        String errorMessage = "url.invalid";
                        baseDataValidator.reset()
                                .failWithCodeNoParameterAddedToErrorCode(
                                        errorMessage);
                    }
                }
            }
        }

        if (events == null || events.isEmpty()) {
            final String errorMessage = "registered.events.cannot.be.empty";
            baseDataValidator.reset().failWithCodeNoParameterAddedToErrorCode(
                    errorMessage);
        }

        final Set<Schema> fields = template.getSchema();
        for (final Schema field : fields) {
            if (!field.isOptional()) {
                boolean found = false;
                for (final HookConfiguration conf : config) {
                    if (field.getFieldName().equals(conf.getFieldName())) {
                        found = true;
                    }
                }
                if (!found) {
                    final String errorMessage = "required.config.field."
                            + "not.provided";
                    baseDataValidator
                            .reset()
                            .value(field.getFieldName())
                            .failWithCodeNoParameterAddedToErrorCode(
                                    errorMessage);
                }
            }
        }

        if (!dataValidationErrors.isEmpty()) {
            throw new PlatformApiDataValidationException(dataValidationErrors);
        }
    }

    private void handleHookDataIntegrityIssues(final JsonCommand command, final Throwable realCause,
            final Exception dve) {
        if (realCause.getMessage().contains("hook_name")) {
            final String name = command.stringValueOfParameterNamed("name");
            throw new PlatformDataIntegrityException(
                    "error.msg.hook.duplicate.name", "A hook with name '"
                            + name + "' already exists", "name", name);
        }

        throw new PlatformDataIntegrityException(
                "error.msg.unknown.data.integrity.issue",
                "Unknown data integrity issue with resource: "
                        + realCause.getMessage());
    }
}
