
package af.gov.anar.dck.report.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.report.model.Report;
import af.gov.anar.dck.report.repository.ReportRepository;
import af.gov.anar.dck.useradministration.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    @Loggable
    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Report create(Report report) {
        report.setEnvSlug(userService.getCurrentEnv());
        return reportRepository.save(report);
    }

    @Loggable
    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public boolean update(Long id, Report report) {
        if (id != null) {
            report.setId(id);
            report.setEnvSlug(userService.getCurrentEnv());
            report.setForm(findById(id).getForm());
            reportRepository.save(report);
            return true;
        }
        return false;
    }

    @Loggable
    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Loggable
    @Override
    public List<Report> findAllWithoutXMLContent() {
        String envSlug = userService.getCurrentEnv();
        return reportRepository.findAllWithoutXMLContent(envSlug);
    }

    @Loggable
    @Override
    public List<Report> findAllWithoutXMLContentByEnv(String envSlug) {
        return reportRepository.findAllWithoutXMLContent(envSlug);
    }

    @Loggable
    @Override
    public List<Report> findAllByFormWithoutXMLContent(Long formId) {
        String envSlug = userService.getCurrentEnv();
        return reportRepository.findAllByFormWithoutXMLContent(formId, envSlug);
    }

    @Loggable
    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Report findById(Long id) {
        return reportRepository.getOne(id);
    }

    @Loggable
    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public List<Report> findByFormId(Long id) {
        return reportRepository.findByFormId(id);
    }

    @Loggable
    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public boolean delete(Long id) {
        Report report = reportRepository.getOne(id);
        if (report != null) {
            reportRepository.delete(report);
            return true;
        }
        return false;
    }

    @Loggable
    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void delete(Report report) {
        if (report != null) {
            reportRepository.delete(report);
        }
    }

    @Loggable
    @Override
    public File generatePdfJasperReport(File xmlDatasource, Map<String, Object> parameters, File jrxmlFile,
            File destFile) throws IOException, JRException {
        // Get your data source
        // JRBeanCollectionDataSource jrBeanCollectionDataSource = new
        // JRBeanCollectionDataSource(propertiesList);

        String jrxmlFilePath = jrxmlFile.getAbsolutePath();

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilePath);

        Map<String, Object> params = new HashMap<>();

        Document document = JRXmlUtils.parse(JRLoader.getLocationInputStream(xmlDatasource.getAbsolutePath()));
        params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params);

        // final JRPropertiesUtil jrProps =
        // JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());

        // jrProps.setProperty(JRFont.DEFAULT_PDF_FONT_NAME, "Times New Roman");
        // jrProps.setProperty(JRFont.DEFAULT_PDF_ENCODING, "Identity-H");
        // jrProps.setProperty(JRFont.DEFAULT_PDF_EMBEDDED, "True");

        JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.font.name", "Arial");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.embedded", "true");
        jasperReportsContext.setProperty("net.sf.jasperreports.export.character.encoding", "UTF-8");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.font.name", "Arial");
        // jasperReportsContext.setProperty("net.sf.jasperreports.default.font.size",
        // "14");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");

        // JRDesignStyle normalStyle = new JRDesignStyle();
        // normalStyle.setName("times");
        // normalStyle.setDefault(true);
        // normalStyle.setFontName("");
        // normalStyle.setPdfFontName("");
        // normalStyle.setPdfEncoding("Identity-H");
        // normalStyle.setPdfEmbedded(true);
        // jasperPrint.addStyle(normalStyle);

        // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
        // jrBeanCollectionDataSource);

        // Export the report to a PDF file
        JasperExportManager.exportReportToPdfFile(jasperPrint, destFile.getAbsolutePath());
        return destFile;
    }

}
