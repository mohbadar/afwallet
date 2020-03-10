
package af.gov.anar.dck.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.report.model.Report;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    public List<Report> findByFormId(Long formId);

    @Query("SELECT new af.gov.anar.dck.report.model.Report(r.id, r.name, r.description, r.envSlug, r.createdAt) from Report r where r.envSlug = ?1 order by r.id")
    public List<Report> findAllWithoutXMLContent(String envSlug);

    @Query("SELECT new af.gov.anar.dck.report.model.Report(r.id, r.name, r.description, r.envSlug, r.createdAt) from Form f INNER JOIN f.reports r where f.id = ?1 and r.envSlug = ?2 order by r.id")
    public List<Report> findAllByFormWithoutXMLContent(Long formId, String envSlug);
}
