
package af.gov.anar.spm.spm.repository;


import af.gov.anar.spm.spm.domain.Scorecard;
import af.gov.anar.spm.spm.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScorecardRepository extends JpaRepository<Scorecard, Long> {

    List<Scorecard> findBySurvey(final Survey survey);
//    List<Scorecard> findBySurveyAndClient(final Survey survey, final Client client);
}
