

package af.asr.notification.mapper;

import af.asr.notification.domain.Template;
import af.asr.notification.model.TemplateEntity;

public class TemplateMapper {
	
	private TemplateMapper() {
		super();
	}
	
	public static Template map(final TemplateEntity templateEntity) {
		final Template template = new Template();
		template.setTemplateIdentifier(templateEntity.getTemplateIdentifier());
		template.setSenderEmail(templateEntity.getSenderEmail());
		template.setSubject(templateEntity.getSubject());
		template.setMessage(templateEntity.getMessage());
		template.setUrl(templateEntity.getUrl());
		return template;
	}
	
	public static TemplateEntity map(final Template template) {
		final TemplateEntity templateEntity = new TemplateEntity();
		templateEntity.setTemplateIdentifier(template.getTemplateIdentifier());
		templateEntity.setSubject(template.getSubject());
		templateEntity.setSenderEmail(template.getSenderEmail());
		templateEntity.setMessage(template.getMessage());
		templateEntity.setUrl(template.getUrl());
		return templateEntity;
	}
}

