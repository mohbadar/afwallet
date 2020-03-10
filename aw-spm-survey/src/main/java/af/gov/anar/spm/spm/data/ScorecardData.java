
package af.gov.anar.spm.spm.data;

import java.util.ArrayList;
import java.util.List;

public class ScorecardData {

    private Long id;
    private Long userId;
    private String username;
    private Long clientId;
    private Long surveyId;
    private String surveyName;
    private List<ScorecardValue> scorecardValues;

    public ScorecardData() {
        super();
    }

    private ScorecardData(final Long id, final Long userId, final String username, final Long surveyId, final String surveyName,
            final Long clientId) {
        this.id = id;
        this.userId = userId;
        this.clientId = clientId;
        this.scorecardValues = new ArrayList<>();
        this.surveyId = surveyId;
        this.surveyName = surveyName;
        this.username = username;
    }

    public static ScorecardData instance(final Long id, final Long userId, final String username, final Long surveyId,
            final String surveyName, final Long clientId) {
        return new ScorecardData(id, userId, username, surveyId, surveyName, clientId);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getClientId() {
        return clientId;
    }

    public List<ScorecardValue> getScorecardValues() {
        return scorecardValues;
    }

    public void setScorecardValues(List<ScorecardValue> scorecardValues) {
        if (this.scorecardValues == null) {
            this.scorecardValues = new ArrayList<>();
        }
        this.scorecardValues.addAll(scorecardValues);
    }

    public String getUsername() {
        return this.username;
    }

    public Long getSurveyId() {
        return this.surveyId;
    }

    public String getSurveyName() {
        return this.surveyName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

}
