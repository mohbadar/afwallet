package af.gov.anar.dck.form.service;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.xml.sax.SAXException;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.useradministration.model.User;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FormService {
    public Form create(Form form, String childFormsIds) throws SAXException, IOException, ParserConfigurationException;

    public Form create(Form form) throws SAXException, IOException, ParserConfigurationException;

    public boolean delete(Long id);

    public List<Form> findAll();

    public List<Form> findAllByGroup();

    public List<Form> findAllWithoutXMLContent();

    public List<Form> findAllWithoutXMLContentByEnv(String envSlug);

    public List<Form> findByParentFormIdWithoutXMLContent(Long parentFormId);

    public List<Form> findAllWithInstanceCount();

    public List<Form> findAllWithInstanceCountByEnv(String envSlug);

    public Object[] findAllWithInstanceCountByEnv(DataTablesInput input);

    public Form findById(Long id);

    public Form findByXmlFormId(String xmlFormId);

    public boolean updateFormGrid(Long id, String formGrid);

    public boolean update(Long id, Form form);

    public List<Form> findByShowOnMap(Boolean showOnMap);

    public List<Form> findByFormCategory(String formCategory);

    public List<Form> findByFormCategoryAndEnvSlug(int formCategory, String envSlug);

    public boolean getMainMapForms();

    public long countByEnvSlug(String envSlug);

    public long countByEnvSlugAndActive(String envSlug, Boolean active);

    public List<Form> findAllByShowOnMapHasGeometry(boolean showOnMap, boolean hasGeometry);

    // excel export import template
    public Instance getBlankInstance(Form form);

    public Instance getBlankInstanceXmlContentWithHeaders(Form form);

    public File excelExport(List<Instance> instances, String lang, boolean humanReadable) throws IOException;

    public File getExcelImportTemplate(Instance instance) throws IOException;

    public boolean isValidExcelImportTemplate(File file);

    public boolean excelImport(File file, Form form, User user) throws IOException, ParserConfigurationException;

    public List<File> getFiles(File directory, String[] extentions, boolean recursive);

    public List<String> getImages(Form form);

    public List<String> getImagesByEnv(Form form, String envSlug);
}
