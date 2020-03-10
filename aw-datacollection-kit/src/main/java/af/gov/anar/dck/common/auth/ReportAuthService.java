package af.gov.anar.dck.common.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.report.model.Report;
import af.gov.anar.dck.report.service.ReportService;

import java.util.List;

/**
 * @author Jalil haidari
 * 
 *         Class to check if the user has the authority of the action for which
 *         he has requested.
 * @PreAuthorized annotation of spring-boot is used to check if particular
 *                authority is present in Principal object for current user.
 *
 */

@Service
public class ReportAuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReportService reportService;

    @PreAuthorize("hasAuthority('REPORT_CREATE')")
    public Report create(Report report) {
        logger.info("Entry ReportAuthService>create() - POST");
        return reportService.create(report);
    }

    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public boolean delete(Long id) {
        return reportService.delete(id);
    }

    @PreAuthorize("hasAuthority('REPORT_LIST')")
    public List<Report> findAll() {
        logger.info("Entry ReportAuthService>findAll() - GET");
        return reportService.findAll();
    }

    @PreAuthorize("hasAuthority('REPORT_LIST')")
    public List<Report> findAllWithoutXMLContent() {
        logger.info("Entry ReportAuthService>findAllWithoutXMLContent() - GET");
        return reportService.findAllWithoutXMLContent();
    }

    @PreAuthorize("hasAuthority('REPORT_LIST')")
    public List<Report> findAllWithoutXMLContentByEnv(String envSlug) {
        logger.info("Entry ReportAuthService>findAllWithoutXMLContent() - GET");
        return reportService.findAllWithoutXMLContentByEnv(envSlug);
    }

    public List<Report> findAllByFormWithoutXMLContent(Long formId) {
        logger.info("Entry ReportAuthService>findAllByFormWithoutXMLContent() - GET");
        return reportService.findAllByFormWithoutXMLContent(formId);
    }

    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Report findById(Long id) {
        logger.info("Entry ReportAuthService>findById - GET");
        return reportService.findById(id);
    }

    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public List<Report> findByFormId(Long id) {
        logger.info("Entry ReportAuthService>findByFormId - GET");
        return reportService.findByFormId(id);
    }

    @PreAuthorize("hasAuthority('REPORT_EDIT')")
    public boolean update(Long id, Report report) {
        logger.info("Entry ReportAuthService>update() - PUT");
        return reportService.update(id, report);
    }
}
