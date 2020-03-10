
package af.gov.anar.spm.spm.service;

import af.gov.anar.spm.spm.domain.Scorecard;
import af.gov.anar.spm.spm.domain.Survey;
import af.gov.anar.spm.spm.repository.ScorecardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScorecardService {

    private final ScorecardRepository scorecardRepository;

    @Autowired
    public ScorecardService(final ScorecardRepository scorecardRepository) {
        super();
        this.scorecardRepository = scorecardRepository;
    }

    public List<Scorecard> createScorecard(final List<Scorecard> scorecards) {
        return scorecardRepository.saveAll(scorecards);
    }

    public List<Scorecard> findBySurvey(final Survey survey) {
        return this.scorecardRepository.findBySurvey(survey);
    }

    public List<Scorecard> findBySurveyAndClient(final Survey survey, final Object client) {

//        return this.scorecardRepository.findBySurveyAndClient(survey, client);
        return null;
    }

}
