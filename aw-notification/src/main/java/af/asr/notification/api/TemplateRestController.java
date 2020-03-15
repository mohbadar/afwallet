package af.asr.notification.api;

import af.asr.notification.ServiceConstants;
import af.asr.notification.domain.Template;
import af.asr.notification.service.TemplateService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/template")
public class TemplateRestController {
	
	private final Logger logger;
	private final TemplateService templateService;
	
	@Autowired
	public TemplateRestController(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
	                              final TemplateService templateService) {
		super();
		this.logger = logger;
		this.templateService = templateService;
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			value = "/{identifier}",
			method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Template> findTemplateByIdentifier(@PathVariable("identifier") final String identifier) {
		return this.templateService.findTemplateWithIdentifier(identifier)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> ServiceException.notFound("Template with identifier " + identifier + " doesn't exist."));
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> createTemplate(@RequestBody @Valid final Template template) throws InterruptedException {
		if (this.templateService.templateExists(template.getTemplateIdentifier())) {
			throw ServiceException.conflict("Template {0} already exists.", template.getTemplateIdentifier());
		}
		
		this.templateService.createTemplate(template);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> updateTemplate(@RequestBody @Valid final Template template) {
		this.templateService.updateTemplate(template);
		return ResponseEntity.accepted().build();
	}
	
	//@Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
	@RequestMapping(value = "/{identifier}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
    ResponseEntity<Void> deleteTemplate(@PathVariable @Valid final String identifier) {
		this.templateService.deleteTemplate(identifier);
		return ResponseEntity.ok().build();
	}
}
