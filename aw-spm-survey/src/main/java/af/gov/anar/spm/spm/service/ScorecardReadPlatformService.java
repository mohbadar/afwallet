
package af.gov.anar.spm.spm.service;

import java.util.Collection;

import af.gov.anar.spm.spm.data.ScorecardData;
import org.springframework.stereotype.Component;

@Component
public interface ScorecardReadPlatformService {
    
    Collection<ScorecardData> retrieveScorecardByClient(final Long clientId);
    
    Collection<ScorecardData> retrieveScorecardBySurveyAndClient(final Long surveyId, final Long clientId);
    
    Collection<ScorecardData> retrieveScorecardBySurvey(final Long surveyId);
}
