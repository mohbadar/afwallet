
package af.gov.anar.corona.notification.service;

import af.gov.anar.corona.notification.ServiceConstants;
import af.gov.anar.corona.notification.domain.Template;
import af.gov.anar.corona.notification.mapper.TemplateMapper;
import af.gov.anar.corona.notification.model.TemplateEntity;
import af.gov.anar.corona.notification.repository.TemplateRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TemplateService {
	
	
	private final TemplateRepository templateRepository;
	private Logger logger;
	
	@Autowired
	public TemplateService(final TemplateRepository templateRepository,
	                       @Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger) {
		super();
		this.logger = logger;
		this.templateRepository = templateRepository;
	}
	public Optional<Template> findTemplateWithIdentifier(final String identifier) {
		return this.templateRepository.findByTemplateIdentifier(identifier).map(TemplateMapper::map);
	}
	
	public Boolean templateExists(final String identifier) {
		return this.templateRepository.existsByTemplateIdentifier(identifier);
	}
	
	public Boolean deleteTemplate(final String identifier) {
		//Todo: Remove html template and template record from repository
		return false;
	}

	@Transactional
	public String createTemplate(final Template template) {
		final TemplateEntity entity = TemplateMapper.map(template);
		this.templateRepository.save(entity);
		return template.getTemplateIdentifier();
	}

	@Transactional
	public String updateTemplate(final Template template)
	{
		final TemplateEntity templateEntity =TemplateMapper.map(template);
		this.templateRepository.delete(templateEntity);
		return this.templateRepository.save(templateEntity).getTemplateIdentifier();
	}


	@Transactional
	public String delteTemplate(final String templateIdentifier)
	{
		final  TemplateEntity templateEntity = this.templateRepository.findByTemplateIdentifier(templateIdentifier).get();
		this.templateRepository.delete(templateEntity);
		return templateIdentifier;
	}
}
