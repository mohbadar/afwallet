package af.gov.anar.ebreshna.configuration.common.workingcalender.repository;

import af.gov.anar.ebreshna.configuration.common.workingcalender.model.WorkingCalenderTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingCalenderTemplateRepository extends JpaRepository<WorkingCalenderTemplate, Long> {
}
