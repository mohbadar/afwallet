
package af.gov.anar.hooks.template.service;

import java.util.List;

import af.gov.anar.hooks.infrastructure.common.command.JsonCommand;
import af.gov.anar.hooks.template.domain.Template;
import af.gov.anar.hooks.template.domain.TemplateEntity;
import af.gov.anar.hooks.template.domain.TemplateType;
import af.gov.anar.lang.data.CommandProcessingResult;

public interface TemplateDomainService {

    List<Template> getAll();

    List<Template> getAllByEntityAndType(TemplateEntity entity, TemplateType type);

    Template findOneById(Long id);

    Template updateTemplate(Template template);

    CommandProcessingResult createTemplate(final JsonCommand command);

    CommandProcessingResult updateTemplate(final Long templateId, final JsonCommand command);

    CommandProcessingResult removeTemplate(final Long templateId);
}
