
package af.gov.anar.spm.spm.domain;

import java.util.ArrayList;
import java.util.List;

import af.gov.anar.lang.data.ApiParameterError;
import af.gov.anar.lang.infrastructure.exception.common.PlatformApiDataValidationException;
import af.gov.anar.lang.validation.DataValidatorBuilder;
import af.gov.anar.spm.spm.util.SurveyApiConstants;
import org.springframework.stereotype.Component;

@Component
public class SurveyValidator {

    public void validate(final Survey survey) {
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();

        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
                .resource(SurveyApiConstants.SURVEY_RESOURCE_NAME);

        baseDataValidator.reset().parameter(SurveyApiConstants.keyParamName).value(survey.getKey()).notNull().notBlank()
                .notExceedingLengthOf(SurveyApiConstants.maxKeyLength);

        baseDataValidator.reset().parameter(SurveyApiConstants.nameParamName).value(survey.getName()).notNull().notBlank()
                .notExceedingLengthOf(SurveyApiConstants.maxNameLength);

        baseDataValidator.reset().parameter(SurveyApiConstants.countryCodeParamName).value(survey.getCountryCode()).notNull().notBlank()
                .notExceedingLengthOf(SurveyApiConstants.maxCountryCodeLength);
        baseDataValidator.reset().parameter(SurveyApiConstants.descriptionParamName).value(survey.getDescription()).ignoreIfNull()
                .notExceedingLengthOf(SurveyApiConstants.maxDescriptionLength);
        List<Question> questions = survey.getQuestions();
        baseDataValidator.reset().parameter(SurveyApiConstants.questionParamName).value(questions).notNull();
        validateQuestions(baseDataValidator, questions);
        throwExceptionIfValidationWarningsExist(dataValidationErrors);

    }

    private void validateQuestions(final DataValidatorBuilder baseDataValidator, List<Question> questions) {
        if (questions != null) {
            baseDataValidator.reset().parameter(SurveyApiConstants.questionParamName + "." + SurveyApiConstants.lengthParamName)
                    .value(questions.toArray()).arrayNotEmpty();
            for (Question question : questions) {
                baseDataValidator.reset().parameter(SurveyApiConstants.questionParamName + "." + SurveyApiConstants.keyParamName)
                        .value(question.getKey()).notNull().notExceedingLengthOf(SurveyApiConstants.maxKeyLength);
                baseDataValidator.reset().parameter(SurveyApiConstants.questionParamName + "." + SurveyApiConstants.textParamName)
                        .value(question.getText()).notNull().notExceedingLengthOf(SurveyApiConstants.maxTextLength);
                baseDataValidator.reset().parameter(SurveyApiConstants.questionParamName + "." + SurveyApiConstants.descriptionParamName)
                        .value(question.getDescription()).ignoreIfNull().notExceedingLengthOf(SurveyApiConstants.maxDescriptionLength);
                validateOptions(baseDataValidator, question);

            }
        }
    }

    private void validateOptions(final DataValidatorBuilder baseDataValidator, Question question) {
        List<Response> responses = question.getResponses();
        baseDataValidator.reset().parameter(SurveyApiConstants.questionParamName + "." + SurveyApiConstants.optionsParamName)
                .value(responses).notNull();
        if (responses != null) {
            baseDataValidator.reset().parameter(SurveyApiConstants.questionParamName + "." + SurveyApiConstants.optionsParamName)
                    .value(responses.toArray()).arrayNotEmpty();
            for (Response response : responses) {
                baseDataValidator.reset().parameter(SurveyApiConstants.optionsParamName + "." + SurveyApiConstants.textParamName)
                        .value(response.getText()).notNull().notExceedingLengthOf(SurveyApiConstants.maxTextLength);
                baseDataValidator.reset().parameter(SurveyApiConstants.optionsParamName + "." + SurveyApiConstants.valueParamName)
                        .value(response.getValue()).notNull().notGreaterThanMax(SurveyApiConstants.maxOptionsValue);
            }
        }
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) {
            throw new PlatformApiDataValidationException(dataValidationErrors);
        }
    }

}
