package af.gov.anar.dck.common.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.useradministration.model.User;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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
public class FormAuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FormService formService;

    @PreAuthorize("hasAuthority('FORM_CREATE')")
    public Form create(Form form) throws SAXException, IOException, ParserConfigurationException {
        logger.info("Entry FormAuthService>create() - POST");
        return formService.create(form);
    }

    public Form create(Form form, String childFormsId) throws SAXException, IOException, ParserConfigurationException {
        return formService.create(form, childFormsId);
    }

    @PreAuthorize("hasAuthority('FORM_DELETE')")
    public boolean delete(Long id) {
        return formService.delete(id);
    }

    @PreAuthorize("hasAuthority('FORM_LIST')")
    public List<Form> findAll() {
        logger.info("Entry FormAuthService>findAll() - GET");
        return formService.findAll();
    }

    public List<Form> findByFormCategoryAndEnvSlug(int formCategory, String envSlug) {
        return formService.findByFormCategoryAndEnvSlug(formCategory, envSlug);
    }

    @PreAuthorize("hasAuthority('FORM_LIST')")
    public List<Form> findAllByGroup() {
        logger.info("Entry FormAuthService>findAllByGroup - GET");
        return formService.findAllByGroup();
    }

    @PreAuthorize("hasAuthority('FORM_LIST')")
    public List<Form> findAllWithoutXMLContent() {
        logger.info("Entry FormAuthService>findAllWithoutXMLContent() - GET");
        return formService.findAllWithoutXMLContent();
    }

    @PreAuthorize("hasAuthority('FORM_LIST')")
    public List<Form> findAllWithoutXMLContentByEnv(String envSlug) {
        logger.info("Entry FormAuthService>findAllWithoutXMLContent() - GET");
        return formService.findAllWithoutXMLContentByEnv(envSlug);
    }

    @PreAuthorize("hasAuthority('FORM_LIST')")
    public List<Form> findAllWithInstanceCount() {
        logger.info("Entry FormAuthService>findAllWithInstanceCount() - GET");
        return formService.findAllWithInstanceCount();
    }

    @PreAuthorize("hasAuthority('FORM_LIST')")
    public List<Form> findAllWithInstanceCountByEnv(String envSlug) {
        logger.info("Entry FormAuthService>findAllWithInstanceCountByEnv() - GET");
        return formService.findAllWithInstanceCountByEnv(envSlug);
    }

    @PreAuthorize("hasAuthority('FORM_LIST')")
    public Object[] findAllWithInstanceCountByEnv(DataTablesInput input) {
        logger.info("Entry FormAuthService>findAllWithInstanceCountByEnv() - GET");
        return formService.findAllWithInstanceCountByEnv(input);
    }

    @PreAuthorize("hasAuthority('FORM_VIEW')")
    public Form findById(Long id) {
        logger.info("Entry FormAuthService>findById - GET");
        return formService.findById(id);
    }

    public Form findByXmlFormId(String xmlFormId) {
        logger.info("Entry FormAuthService>findByXmlFormId - GET");
        return formService.findByXmlFormId(xmlFormId);
    }

    @PreAuthorize("hasAuthority('FORM_VIEW')")
    public List<Form> findByParentFormIdWithoutXMLContent(Long id) {
        logger.info("Entry FormAuthService>findByParentFormIdWithoutXMLContent - GET");
        return formService.findByParentFormIdWithoutXMLContent(id);
    }

    @PreAuthorize("hasAuthority('FORM_EDIT')")
    public boolean update(Long id, Form form) {
        logger.info("Entry FormAuthService>update() - PUT");
        return formService.update(id, form);
    }

    public File excelExport(List<Instance> instances, String lang, boolean humanReadable) throws IOException {
        logger.info("Entry FormAuthService>excelExport()");
        return formService.excelExport(instances, lang, humanReadable);
    }

    public boolean excelImport(File file, Form form, User user) throws IOException, ParserConfigurationException {
        logger.info("Entry FormAuthService>excelExport()");
        return formService.excelImport(file, form, user);
    }

    public Instance getBlankInstance(Form form) {
        logger.info("Entry FormAuthService>getBlankInstance() - PUT");
        return formService.getBlankInstance(form);
    }

    public Instance getBlankInstanceXmlContentWithHeaders(Form form) {
        return formService.getBlankInstanceXmlContentWithHeaders(form);
    }

    public File getExcelImportTemplate(Instance instance) throws IOException {
        logger.info("Entry FormAuthService>getExcelImportTemplate() - PUT");
        return formService.getExcelImportTemplate(instance);
    }

    @PreAuthorize("hasAuthority('FORM_EDIT')")
    public boolean updateFormGrid(Long id, String formGrid) {
        logger.info("Entry FormAuthService>updateFormGrid() - PUT");
        return formService.updateFormGrid(id, formGrid);
    }
}
