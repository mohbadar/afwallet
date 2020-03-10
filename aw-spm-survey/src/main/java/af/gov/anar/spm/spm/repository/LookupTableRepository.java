
package af.gov.anar.spm.spm.repository;



import af.gov.anar.spm.spm.domain.LookupTable;
import af.gov.anar.spm.spm.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LookupTableRepository extends JpaRepository<LookupTable, Long> {

    List<LookupTable> findBySurvey(final Survey survey);
    List<LookupTable> findByKey(final String spmKey);
}
