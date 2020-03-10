
package af.gov.anar.spm.spm.exception;


import af.gov.anar.lang.infrastructure.exception.common.AbstractPlatformDomainRuleException;

public class SurveyResponseNotAvailableException extends AbstractPlatformDomainRuleException {

    public SurveyResponseNotAvailableException() {
        super("error.msg.no.survey.response","No response available for survey.");
    }

}
