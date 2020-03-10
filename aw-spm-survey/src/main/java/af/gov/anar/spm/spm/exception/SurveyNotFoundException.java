
package af.gov.anar.spm.spm.exception;


import af.gov.anar.lang.infrastructure.exception.common.AbstractPlatformResourceNotFoundException;

public class SurveyNotFoundException extends AbstractPlatformResourceNotFoundException {

    public SurveyNotFoundException(final Long id) {
        super("error.msg.survey.id.notfound", "Survey with id " + id + " not found!", id);
    }
}
