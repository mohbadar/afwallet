
package af.gov.anar.spm.spm.repository;

import af.gov.anar.spm.spm.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("select s from Survey s where :pointInTime between s.validFrom and s.validTo")
    List<Survey> fetchActiveSurveys(@Param("pointInTime") final Date pointInTime);
    
    @Query("select s from Survey s ")
    List<Survey> fetchAllSurveys();

    @Query("select s from Survey s where s.key = :key and :pointInTime between s.validFrom and s.validTo")
    Survey findByKey(@Param("key") final String key, @Param("pointInTime") final Date pointInTime);
}
