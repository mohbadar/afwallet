
package af.gov.anar.spm.spm.util;

import af.gov.anar.lib.date.DateUtility;
import af.gov.anar.spm.spm.data.ScorecardData;
import af.gov.anar.spm.spm.data.ScorecardValue;
import af.gov.anar.spm.spm.domain.Question;
import af.gov.anar.spm.spm.domain.Response;
import af.gov.anar.spm.spm.domain.Scorecard;
import af.gov.anar.spm.spm.domain.Survey;
import af.gov.anar.spm.spm.exception.SurveyResponseNotAvailableException;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.List;


public class ScorecardMapper {

    private ScorecardMapper() {
        super();
    }

    public static List<Scorecard> map(final ScorecardData scorecardData, final Survey survey) {
        final List<Scorecard> scorecards = new ArrayList<>();

        final List<ScorecardValue> scorecardValues = scorecardData.getScorecardValues();

        if (scorecardValues != null && !scorecardValues.isEmpty()) {
           for (ScorecardValue scorecardValue : scorecardValues) {
               final Scorecard scorecard = new Scorecard();
               scorecards.add(scorecard);
               scorecard.setSurvey(survey);
               ScorecardMapper.setQuestionAndResponse(scorecardValue, scorecard, survey);
//               scorecard.setAppUser(appUser);
//               scorecard.setClient(client);
//               scorecard.setCreatedOn(DateUtility.getUTCCurrentDateTimeString().toLocalDate());
               scorecard.setValue(scorecardValue.getValue());
           }
        }else{
            throw new SurveyResponseNotAvailableException();
        }
        return scorecards;
    }

    private static void setQuestionAndResponse(final ScorecardValue scorecardValue, final Scorecard scorecard,
                                        final Survey survey) {
        final List<Question> questions = survey.getQuestions();
        for (final Question question : questions) {
            if (question.getId().equals(scorecardValue.getQuestionId())) {
                scorecard.setQuestion(question);
                for (final Response response : question.getResponses()) {
                    if (response.getId().equals(scorecardValue.getResponseId())) {
                        scorecard.setResponse(response);
                        break;
                    }
                }
                break;
            }
        }
    }
}