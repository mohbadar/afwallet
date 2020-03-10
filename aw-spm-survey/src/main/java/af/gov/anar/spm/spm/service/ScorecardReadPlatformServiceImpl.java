
package af.gov.anar.spm.spm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import af.gov.anar.spm.spm.data.ScorecardData;
import af.gov.anar.spm.spm.data.ScorecardValue;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

@Service
public class ScorecardReadPlatformServiceImpl implements ScorecardReadPlatformService{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static final class ScorecardMapper implements RowMapper<ScorecardData> {

        public String schema() {
            StringBuilder sb = new StringBuilder(50);
            sb.append(" sc.id as id, sc.survey_id as surveyId, s.a_name as surveyName, ");
            sb.append(" sc.client_id as clientId,");
            sb.append(" sc.user_id as userId, user.username as username ");
            sb.append(" from m_survey_scorecards sc ");
            sb.append(" left join m_surveys s ON s.id = sc.survey_id ");
            sb.append(" left join m_appuser user ON user.id = sc.user_id ");

            return sb.toString();
        }

        @Override
        public ScorecardData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long id = rs.getLong("id");
            final Long surveyId = rs.getLong("surveyId");
            final String surveyName = rs.getString("surveyName");
            final Long clientId = rs.getLong("clientId");
            final Long userId = rs.getLong("userId");
            final String username = rs.getString("username");

            return ScorecardData.instance(id, userId, username, surveyId, surveyName, clientId);
        }
    }

    private static final class ScorecardValueMapper implements RowMapper<ScorecardValue> {

        public String schema() {
            StringBuilder sb = new StringBuilder(50);
            sb.append(" sc.question_id as questionId, sc.response_id as responseId, ");
            sb.append(" sc.created_on as createdOn, sc.a_value as value ");
            sb.append(" from m_survey_scorecards sc  ");
            sb.append(" where sc.survey_id = ? and sc.client_id = ?  ");

            return sb.toString();
        }

        @Override
        public ScorecardValue mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long questionId = rs.getLong("questionId");
            final Long responseId = rs.getLong("responseId");
            final Date createdOn = rs.getDate("createdOn");
            final Integer value = rs.getInt("value");

            return ScorecardValue.instance(questionId, responseId, value, createdOn);
        }
    }

    List<ScorecardValue> getScorecardValueBySurveyAndClient(final Long surveyId, final Long clientId) {
        ScorecardValueMapper scvm = new ScorecardValueMapper();
        String sql = "select " + scvm.schema();
        return this.jdbcTemplate.query(sql, scvm, new Object[] { surveyId, clientId });
    }

    Collection<ScorecardData> updateScorecardValues(Collection<ScorecardData> scorecard) {
        for (ScorecardData scorecardData : scorecard) {
            scorecardData.setScorecardValues(getScorecardValueBySurveyAndClient(scorecardData.getSurveyId(), scorecardData.getClientId()));
        }
        return scorecard;
    }


    public Collection<ScorecardData> retrieveScorecardBySurvey(Long surveyId) {
        ScorecardMapper scm = new ScorecardMapper();
        String sql = "select " + scm.schema() + " where sc.survey_id = ? " + " group by sc.survey_id, sc.client_id, sc.id ";
        Collection<ScorecardData> scorecardDatas = this.jdbcTemplate.query(sql, scm, new Object[] { surveyId });
        updateScorecardValues(scorecardDatas);
        return scorecardDatas;
    }


    public Collection<ScorecardData> retrieveScorecardByClient(Long clientId) {
        ScorecardMapper scm = new ScorecardMapper();
        String sql = "select " + scm.schema() + " where sc.client_id = ? " + " group by sc.survey_id, sc.client_id, sc.id ";
        Collection<ScorecardData> scorecardDatas = this.jdbcTemplate.query(sql, scm, new Object[] { clientId });
        updateScorecardValues(scorecardDatas);
        return scorecardDatas;
    }

    public Collection<ScorecardData> retrieveScorecardBySurveyAndClient(Long surveyId, Long clientId) {
        ScorecardMapper scm = new ScorecardMapper();
        String sql = "select " + scm.schema() + " where sc.survey_id = ? and sc.client_id = ? " + " group by sc.survey_id, sc.client_id, sc.id ";
        Collection<ScorecardData> scorecardDatas = this.jdbcTemplate.query(sql, scm, new Object[] { surveyId, clientId });
        updateScorecardValues(scorecardDatas);
        return scorecardDatas;
    }

}
