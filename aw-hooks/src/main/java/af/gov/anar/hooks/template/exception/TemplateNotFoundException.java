
package af.gov.anar.hooks.template.exception;


import af.gov.anar.lang.infrastructure.exception.common.AbstractPlatformResourceNotFoundException;

public class TemplateNotFoundException extends AbstractPlatformResourceNotFoundException {

    public TemplateNotFoundException(final Long id) {
        super("error.msg.template.id.invalid", "Template with identifier " + id + " does not exist", id);
    }
}