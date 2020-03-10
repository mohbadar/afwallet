
package af.gov.anar.hooks.hook.domain;

import af.gov.anar.hooks.hook.api.HookApiConstants;
import af.gov.anar.hooks.infrastructure.common.command.JsonCommand;
import af.gov.anar.hooks.template.domain.Template;
import af.gov.anar.lang.data.AbstractPersistableCustom;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.apache.commons.lang.StringUtils;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


@Entity
@Table(name = "m_hook")
public class Hook extends AbstractPersistableCustom<Long> {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hook", orphanRemoval = true, fetch=FetchType.EAGER)
    private Set<HookResource> events = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hook", orphanRemoval = true, fetch=FetchType.EAGER)
    private Set<HookConfiguration> config = new HashSet<>();

    @ManyToOne(optional = true)
    @JoinColumn(name = "template_id", referencedColumnName = "id", nullable = false)
    private HookTemplate template;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ugd_template_id", referencedColumnName = "id", nullable = true)
    private Template ugdTemplate;

    protected Hook() {
        //
    }

    public static Hook fromJson(final JsonCommand command, final HookTemplate template, final Set<HookConfiguration> config,
                                final Set<HookResource> events, final Template ugdTemplate) {
        final String displayName = command.stringValueOfParameterNamed(HookApiConstants.displayNameParamName);
        Boolean isActive = command.booleanObjectValueOfParameterNamed(HookApiConstants.isActiveParamName);
        if (isActive == null) isActive = false;
        return new Hook(template, displayName, isActive, config, events, ugdTemplate);
    }

    private Hook(final HookTemplate template, final String displayName, final Boolean isActive, final Set<HookConfiguration> config,
            final Set<HookResource> events, final Template ugdTemplate) {

        this.template = template;

        if (StringUtils.isNotBlank(displayName)) {
            this.name = displayName.trim();
        } else {
            this.name = template.getName();
        }
        this.isActive = isActive;
        if (!CollectionUtils.isEmpty(config)) {
            this.config = associateConfigWithThisHook(config);
        }
        if (!CollectionUtils.isEmpty(events)) {
            this.events = associateEventsWithThisHook(events);
        }

        this.ugdTemplate = ugdTemplate;
    }

    private Set<HookConfiguration> associateConfigWithThisHook(final Set<HookConfiguration> config) {
        for (final HookConfiguration hookConfiguration : config) {
            hookConfiguration.update(this);
        }
        return config;
    }

    private Set<HookResource> associateEventsWithThisHook(final Set<HookResource> events) {
        for (final HookResource hookResource : events) {
            hookResource.update(this);
        }
        return events;
    }

    public HookTemplate getHookTemplate() {
        return this.template;
    }

    public Template getUgdTemplate() {
        return this.ugdTemplate;
    }

    public Long getUgdTemplateId() {
        Long templateId = null;
        if (this.ugdTemplate != null) {
            templateId = this.ugdTemplate.getId();
        }
        return templateId;
    }

    public Set<HookConfiguration> getHookConfig() {
        return this.config;
    }

    public Map<String, Object> update(final JsonCommand command) {

        final Map<String, Object> actualChanges = new LinkedHashMap<>(5);

        if (command.isChangeInStringParameterNamed(HookApiConstants.displayNameParamName, this.name)) {
            final String newValue = command.stringValueOfParameterNamed(HookApiConstants.displayNameParamName);
            actualChanges.put(HookApiConstants.displayNameParamName, newValue);
            this.name = newValue;
        }

        if (command.isChangeInBooleanParameterNamed(HookApiConstants.isActiveParamName, this.isActive)) {
            final Boolean newValue = command.booleanObjectValueOfParameterNamed(HookApiConstants.isActiveParamName);
            actualChanges.put(HookApiConstants.isActiveParamName, newValue);
            this.isActive = newValue;
        }

        if (command.isChangeInLongParameterNamed(HookApiConstants.templateIdParamName, getUgdTemplateId())) {
            final Long newValue = command.longValueOfParameterNamed(HookApiConstants.templateIdParamName);
            actualChanges.put(HookApiConstants.templateIdParamName, newValue);
        }

        // events
        if (command.hasParameter(HookApiConstants.eventsParamName)) {
            final JsonArray jsonArray = command.arrayOfParameterNamed(HookApiConstants.eventsParamName);
            if (jsonArray != null) {
                actualChanges.put(HookApiConstants.eventsParamName, jsonArray);
            }
        }

        // config
        if (command.hasParameter(HookApiConstants.configParamName)) {
            final JsonElement element = command.parsedJson().getAsJsonObject().get(HookApiConstants.configParamName);
            if (element != null) {
                actualChanges.put(HookApiConstants.configParamName, element);
            }
        }

        return actualChanges;
    }

    public boolean updateEvents(final Set<HookResource> newHookEvents) {
        if (newHookEvents == null) { return false; }

        if (this.events == null) {
            this.events = new HashSet<>();
        }
        this.events.clear();
        this.events.addAll(associateEventsWithThisHook(newHookEvents));
        return true;
    }

    public boolean updateConfig(final Set<HookConfiguration> newHookConfig) {
        if (newHookConfig == null) { return false; }

        if (this.config == null) {
            this.config = new HashSet<>();
        }
        this.config.clear();
        this.config.addAll(associateConfigWithThisHook(newHookConfig));
        return true;
    }

    public void updateUgdTemplate(final Template ugdTemplate) {
        this.ugdTemplate = ugdTemplate;
    }

}
