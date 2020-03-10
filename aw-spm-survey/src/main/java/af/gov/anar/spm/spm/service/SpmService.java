
package af.gov.anar.spm.spm.service;

import af.gov.anar.lang.infrastructure.exception.common.PlatformDataIntegrityException;
import af.gov.anar.lib.date.DateUtility;
import af.gov.anar.spm.spm.domain.Survey;
import af.gov.anar.spm.spm.domain.SurveyValidator;
import af.gov.anar.spm.spm.exception.SurveyNotFoundException;
import af.gov.anar.spm.spm.repository.SurveyRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

@Service
public class SpmService {

    private final SurveyRepository surveyRepository;
    private final SurveyValidator surveyValidator;

    @Autowired
    public SpmService(final SurveyRepository surveyRepository,
                      final SurveyValidator surveyValidator) {
        super();
        this.surveyRepository = surveyRepository;
        this.surveyValidator = surveyValidator;
    }

    public List<Survey> fetchValidSurveys() {
        return this.surveyRepository.fetchActiveSurveys(new Date());
    }
    
    public List<Survey> fetchAllSurveys() {
        return this.surveyRepository.fetchAllSurveys();
    }

    public Survey findById(final Long id) {
        Survey survey = this.surveyRepository.getOne(id);
        if (survey == null) {
            throw new SurveyNotFoundException(id);
        }
        return survey;
    }

    public Survey createSurvey(final Survey survey) {
        this.surveyValidator.validate(survey);
        final Survey previousSurvey = this.surveyRepository.findByKey(survey.getKey(), new Date());

        if (previousSurvey != null) {
            this.deactivateSurvey(previousSurvey.getId());
        }
        // set valid from to start of today
        Date validFrom = new Date();
        // set valid to for 100 years
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(validFrom);
        cal.add(Calendar.YEAR, 100); 
        survey.setValidFrom(validFrom);
        survey.setValidTo(cal.getTime());
        try {
            this.surveyRepository.saveAndFlush(survey);
        } catch (final EntityExistsException dve) {
            handleDataIntegrityIssues(dve, dve, survey.getKey());
        } catch (final DataIntegrityViolationException dve) {
            handleDataIntegrityIssues(dve.getMostSpecificCause(), dve, survey.getKey());
        } catch (final JpaSystemException dve) {
            handleDataIntegrityIssues(dve.getMostSpecificCause(), dve, survey.getKey());
        } catch (final PersistenceException dve) {
            handleDataIntegrityIssues(dve, dve, survey.getKey());
        }
        return survey ;
    }
    
    public Survey updateSurvey(final Survey survey) {
        try {
            this.surveyValidator.validate(survey);
            this.surveyRepository.saveAndFlush(survey);
        } catch (final EntityExistsException dve) {
            handleDataIntegrityIssues(dve, dve, survey.getKey());
        } catch (final DataIntegrityViolationException dve) {
            handleDataIntegrityIssues(dve.getMostSpecificCause(), dve, survey.getKey());
        } catch (final JpaSystemException dve) {
            handleDataIntegrityIssues(dve.getMostSpecificCause(), dve, survey.getKey());
        } catch (final PersistenceException dve) {
            handleDataIntegrityIssues(dve, dve, survey.getKey());
        }
        return survey;
    }

    public void deactivateSurvey(final Long id) {

        final Survey survey = findById(id);
        final DateTime dateTime = getStartOfToday().minusMillis(1);
        survey.setValidTo(dateTime.toDate());

        this.surveyRepository.save(survey);
    }
    
    public void activateSurvey(final Long id) {

        final Survey survey = findById(id);
        Date validFrom = new Date() ;
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(validFrom);
        cal.add(Calendar.YEAR, 100);
        survey.setValidFrom(validFrom);
        survey.setValidTo(cal.getTime());

        this.surveyRepository.save(survey);
    }
    
    
    public static DateTime getStartOfToday() {
        return DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
    }
    
    private void handleDataIntegrityIssues(final Throwable realCause, final Exception dve, String key) {

        if (realCause.getMessage().contains("m_survey_scorecards")) { throw new PlatformDataIntegrityException(
                "error.msg.survey.cannot.be.modified.as.used.in.client.survey",
                "Survey can not be edited as it is already used in client survey", "name", key); }

        if (realCause.getMessage().contains("key")) { throw new PlatformDataIntegrityException("error.msg.survey.duplicate.key",
                "Survey with key already exists", "name", key); }

        throw new PlatformDataIntegrityException("error.msg.survey.unknown.data.integrity.issue",
                "Unknown data integrity issue with resource: " + realCause.getMessage());
    }
}
