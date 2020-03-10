package af.gov.anar.dck.form.service;

import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.repository.FormRepository;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.repository.InstanceRepository;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.infrastructure.util.ExcelUtil;
import af.gov.anar.dck.infrastructure.util.JsonParserUtil;
import af.gov.anar.dck.infrastructure.util.QueryBuilderUtil;
import af.gov.anar.dck.infrastructure.util.XmlParserUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class FormServiceImpl implements FormService {
	Logger logger = LoggerFactory.getLogger(FormServiceImpl.class);

	@Value("${app.upload.dir}")
	private String uploadDir;

	@Autowired
	private FormRepository formRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private InstanceRepository instanceRepository;

	@Autowired
	private XmlParserUtil xmlParserUtil;

	@Autowired
	private JsonParserUtil jsonParserUtil;

	@Autowired
	private ExcelUtil excelUtil;

	@Autowired
	private QueryBuilderUtil queryBuilderUtil;

	@Autowired
	private InstanceService instanceService;
	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	private UserService userService;

	@Override
	public Form create(Form form) throws SAXException, IOException, ParserConfigurationException {
		xmlParserUtil.isValidThrowException(form.getXmlContent());
		addFormRequiredPropertiesToXML(form);
		setFormRequiredPropertiesFromXML(form);
		return formRepository.save(form);
	}

	@Override
	public Form create(Form form, String childFormsId) throws SAXException, IOException, ParserConfigurationException {
		xmlParserUtil.isValidThrowException(form.getXmlContent());
		addFormRequiredPropertiesToXML(form);
		setFormRequiredPropertiesFromXML(form);

		if (childFormsId.length() > 0) {

			String[] cFormIds = childFormsId.split(",");
			// convert string[] to long[]
			List<Long> formsIds = new ArrayList<Long>();
			for (int i = 0; i < cFormIds.length; i++) {
				formsIds.add(Long.parseLong(cFormIds[i]));
			}

			// Add this form to all child forms as parent form
			Collection<Form> childFormsArray = new ArrayList<Form>();
			for (Long formId : formsIds) {
				Form childForm = findById(formId);
				childFormsArray.add(childForm);
			}
			form.setChildForms(childFormsArray);
		}

		form = xmlParserUtil.setAndFetchXFormInstancePropertiesWithMetatdata(form);

		return formRepository.save(form);

	}

	@Override
	public boolean delete(Long id) {
		Form form = findById(id);
		if (form != null) {
			formRepository.delete(form);
			return true;
		}
		return false;
	}

	@Override
	public List<Form> findAll() {
		return formRepository.findAll();
	}

	@Override
	public List<Form> findAllByGroup() {
		List<Form> formsList;
		if (userService.isAdmin()) {
			// if the user is admin, all forms should be shown
			formsList = formRepository.findAll();
		} else {
			Collection<Group> groups = userService.getLoggedInUser().getGroups();
			List<Long> groupIds = new ArrayList<Long>();

			for (Group group : groups) {
				groupIds.add(group.getId());
			}

			formsList = formRepository.findByGroups(groupIds);
		}

		return formsList;
	}

	@Override
	public List<Form> findAllByShowOnMapHasGeometry(boolean showOnMap, boolean hasGeometry) {
		List<Form> formsList;
		if (userService.isAdmin()) {
			// if the user is admin, all forms should be shown
			formsList = formRepository.findAllWithoutXMLContentByShowOnMapHasGeometry(true, true);
		} else {
			Collection<Group> groups = userService.getLoggedInUser().getGroups();
			List<Long> groupIds = new ArrayList<Long>();

			for (Group group : groups) {
				groupIds.add(group.getId());
			}

			formsList = formRepository.findByGroupsShowOnMapHasGeometry(groupIds, true, true);
		}

		return formsList;
	}

	@Override
	public List<Form> findAllWithoutXMLContent() {
		List<Form> formsList;
		if (userService.isAdmin()) {
			// if the user is admin, all forms should be shown
			formsList = formRepository.findAllWithoutXMLContent();
		} else {

			Collection<Group> groups = userService.getLoggedInUser().getGroups();

			List<Long> groupIds = new ArrayList<Long>();

			for (Group group : groups) {
				groupIds.add(group.getId());
			}

			formsList = formRepository.findByGroups(groupIds);
		}

		return formsList;
	}

	@Override
	public List<Form> findAllWithoutXMLContentByEnv(String envSlug) {
		List<Form> formsList;
		if (userService.isAdmin()) {
			// if the user is admin, all forms should be shown
			formsList = formRepository.findAllWithoutXMLContentByEnv(envSlug);
		} else {
			Collection<Group> groups = userService.getLoggedInUser().getGroups();
			List<Long> groupIds = new ArrayList<Long>();
			for (Group group : groups) {
				groupIds.add(group.getId());
			}
			formsList = formRepository.findByGroupsAndEnv(groupIds, envSlug);
		}
		return formsList;
	}

	@Override
	public List<Form> findAllWithInstanceCount() {
		logger.info("Entry FormServiceImpl>findAllWithInstanceCount() - GET");
		// Find all forms according to the user's groups

		List<Form> formsList;
		if (userService.isAdmin()) {
			// if the user is admin, all forms should be shown
			formsList = formRepository.findAllWithInstanceCount();
		} else {
			Collection<Group> groups = userService.getLoggedInUser().getGroups();

			List<Long> groupIds = new ArrayList<Long>();

			for (Group group : groups) {
				groupIds.add(group.getId());
			}

			formsList = formRepository.findByGroups(groupIds);
		}

		return formsList;
	}

	@Override
	public List<Form> findAllWithInstanceCountByEnv(String envSlug) {
		logger.info("Entry FormServiceImpl>findAllWithInstanceCountByEnv() - GET");
		// Find all forms according to the user's groups

		List<Form> formsList;
		if (userService.isAdmin()) {
			// if the user is admin, all forms should be shown
			formsList = formRepository.findAllWithInstanceCountByEnv(envSlug);
		} else {
			Collection<Group> groups = userService.getLoggedInUser().getGroups();
			List<Long> groupIds = new ArrayList<Long>();

			for (Group group : groups) {
				groupIds.add(group.getId());
			}
			formsList = formRepository.findByGroupsAndEnv(groupIds, envSlug);
		}
		return formsList;
	}

	@Override
	public Object[] findAllWithInstanceCountByEnv(DataTablesInput input) {
		logger.info("Entry FormServiceImpl>findAllWithInstanceCountByEnv() - GET");

		Object[] retObj = new Object[2];
		String envSlug = userAuthService.getCurrentEnv();
		int start = input.getStart();
		int limit = input.getLength();
		if (limit < 0) {
			limit = 99999;
		}
		String regex = input.getSearch().getValue();

		Query query;
		List<Object[]> formObjects;
		Long recordTotal;
		// Find all forms according to the user's groups
		List formsList = new ArrayList();
		if (userService.isAdmin()) {
			if (regex != null && !"".equals(regex)) {
				query = this.entityManager.createNativeQuery(
						"SELECT f.id, f.name, f.description, f.xml_form_id, f.form_type, f.form_category, f.active, COUNT(i) AS count from form f left join instance i on f.id=i.form_id WHERE f.env_slug='"
								+ envSlug + "' AND (f.name ILIKE '%" + regex + "%' OR f.form_type ILIKE '" + regex
								+ "%' ) group by f.id limit " + limit
								+ " offset " + start);
				formObjects = query.getResultList();
				recordTotal = getTotalRecords(envSlug, null);

			} else {
				query = this.entityManager.createNativeQuery(
						"SELECT f.id, f.name, f.description, f.xml_form_id, f.form_type, f.form_category, f.active, COUNT(i) AS count from form f left join instance i on f.id=i.form_id WHERE f.env_slug='"
								+ envSlug + "' group by f.id limit " + limit + " offset " + start);
				formObjects = query.getResultList();
				recordTotal = getTotalRecords(envSlug, null);
			}

		} else {
			// Convert the ids to a comman-seperated string to be appended to the query
			// string
			Collection<Group> groups = userService.getLoggedInUser().getGroups();
			StringBuilder builder = new StringBuilder();
			List<Long> groupIds = new ArrayList<Long>();
			for (Group group : groups) {
				groupIds.add(group.getId());
			}
			for (int i = 0; i < groupIds.size() - 1; i++) {
				builder.append(groupIds.get(i) + ", ");
			}
			builder.append(groupIds.get(groupIds.size() - 1));
			String idsString = builder.toString();
			if (regex != null && !"".equals(regex)) {
				query = this.entityManager.createNativeQuery(
						"SELECT f.id, f.name, f.description, f.xml_form_id, f.form_type, f.form_category, f.active, COUNT( distinct i) AS count from form f inner join form_group fg on f.id=fg.form_id LEFT JOIN instance i ON f.id=i.form_id WHERE fg.group_id in ("
								+ idsString + " ) AND (f.name ILIKE '%" + regex + "%' OR f.form_type ILIKE '" + regex
								+ "%') group by f.id limit " + limit
								+ " offset " + start);
				formObjects = query.getResultList();

				recordTotal = getTotalRecords(envSlug, idsString);

			} else {
				query = this.entityManager.createNativeQuery(
						"SELECT f.id, f.name, f.description, f.xml_form_id, f.form_type, f.form_category, f.active, COUNT( distinct i) AS count from form f inner join form_group fg on f.id=fg.form_id LEFT JOIN instance i ON f.id=i.form_id WHERE fg.group_id in ("
								+ idsString + " ) AND f.env_slug='" + envSlug + "' group by f.id limit " + limit
								+ " offset " + start);
				formObjects = query.getResultList();

				recordTotal = getTotalRecords(envSlug, idsString);

			}

		}
		retObj[0] = formObjects;
		retObj[1] = recordTotal;
		return retObj;
	}

	@Override
	public List<Form> findByParentFormIdWithoutXMLContent(Long parentFormId) {
		return formRepository.findByParentFormIdWithoutXMLContent(parentFormId);
	}

	@Override
	public Form findById(Long id) {
		Optional<Form> optionalObj = formRepository.findById(id);
		return optionalObj.get();
	}

	@Override
	public Form findByXmlFormId(String xmlFormId) {
		return formRepository.findByXmlFormId(xmlFormId);
	}

	@Override
	public boolean update(Long id, Form form) {
		if (id != null) {
			form.setId(id);
			addFormRequiredPropertiesToXML(form);
			setFormRequiredPropertiesFromXML(form);
			form = xmlParserUtil.setAndFetchXFormInstancePropertiesWithMetatdata(form);
			formRepository.save(form);
			return true;
		}
		return false;
	}

	// This function is very important to be called alway we create or update a
	// form.
	// It will add envSlug and User property to form
	public Form addFormRequiredPropertiesToXML(Form form) {
		return xmlParserUtil.addEnvSlugToFormXML(form);
	}

	public Form setFormRequiredPropertiesFromXML(Form form) {
		form.setXmlFormId(xmlParserUtil.getXFormInstanceId(form));
		return form;
	}

	@Override
	public List<Form> findByShowOnMap(Boolean showOnMap) {
		return formRepository.findByShowOnMap(showOnMap);
	}

	@Override
	public List<Form> findByFormCategory(String formCategory) {
		return formRepository.findByFormCategory(formCategory);
	}

	@Override
	public List<Form> findByFormCategoryAndEnvSlug(int formCategory, String envSlug) {
		return formRepository.findByFormCategoryAndEnvSlug(formCategory, envSlug);
	}

	@Override
	public boolean getMainMapForms() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateFormGrid(Long id, String formGrid) {
		Form form = formRepository.getOne(id);
		if (formGrid.length() > 0) {
			form.setGridJson(formGrid);
			formRepository.save(form);
			return true;
		}
		return false;
	}

	@Override
	public long countByEnvSlug(String envSlug) {
		return formRepository.countByEnvSlug(envSlug);
	}

	@Override
	public long countByEnvSlugAndActive(String envSlug, Boolean active) {
		return formRepository.countByEnvSlugAndActive(envSlug, active);
	}

	private XSSFSheet renderColumnRow(XSSFSheet workSheet, List<String> columns, int columnRowIndex,
                                      JSONArray formPropertiesJson, String lang) {

		Row headerRow = workSheet.createRow(columnRowIndex);
		for (int i = 0; i < columns.size(); i++) {
			Cell columnCell = headerRow.createCell(i);
			if (formPropertiesJson != null) {
				columnCell.setCellValue(getPropertyLabel(columns.get(i), formPropertiesJson, lang));
			} else {
				columnCell.setCellValue(columns.get(i));
			}
		}
		return workSheet;
	}

	private String getPropertyLabel(String property, JSONArray formPropertiesJson, String lang) {
		String label = "";

		// if property does not have / then it means it is not xmlform field but it is
		// header field
		if (property.indexOf('/') == -1) {
			return property;
		}

		if (formPropertiesJson != null) {
			for (int i = 0; i < formPropertiesJson.length(); i++) {
				JSONObject pObj = formPropertiesJson.getJSONObject(i);
				String name = pObj.getString("name");
				if (property.equals(name)) {
					if (pObj.has("languages")) {
						JSONObject langObj = pObj.getJSONObject("languages");
						label = langObj.getString(lang);
					} else {
						label = pObj.getString("label");
					}
					return label;
				}
			}
		}
		return label;
	}

	@Override
	public Instance getBlankInstance(Form form) {
		Instance instance = new Instance();
		String xmlString = form.getXmlContent();
		if (xmlParserUtil.isValid(xmlString)) {
			Document doc = xmlParserUtil.parse(xmlString);
			Document targetSubTreeDoc = xmlParserUtil.getXFormInstanceNodeTree(doc);
			String blankInstance = xmlParserUtil.convertToString(targetSubTreeDoc);
			instance.setXmlContent(blankInstance);
			return instance;
		}
		return null;
	}

	@Override
	public Instance getBlankInstanceXmlContentWithHeaders(Form form) {
		Instance instance = getBlankInstance(form);
		return xmlParserUtil.addInstanceHeadersToXMLContent(instance);
	}

	@Override
	public File getExcelImportTemplate(Instance instance) throws IOException {
		File excelFile = File.createTempFile("excel_import_template", ".xlsx");
		FileOutputStream outputStream = new FileOutputStream(excelFile);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet workSheet = workbook.createSheet("instances");
		List<String> instanceColumns = excelUtil.getInstanceColumnsForExcel(instance);
		workSheet = renderColumnRow(workSheet, instanceColumns, 0, null, null);
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		return excelFile;
	}

	@Override
	public boolean isValidExcelImportTemplate(File file) {
		return true;
	}

	// excel export instance and xml parsing
	@Override
	public File excelExport(List<Instance> instances, String lang, boolean humanReadable) throws IOException {
		File excelFile = File.createTempFile("excel_export", ".xlsx");

		FileOutputStream outputStream = new FileOutputStream(excelFile);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet workSheet = workbook.createSheet("instances");

		Form form = null;
		JSONArray formPropertiesJson = null;
		int instanceIndex = 0;
		for (Instance instance : instances) {
			Map<String, Object> properties = instance.getProperties();
			List<String> instanceColumns = excelUtil.getInstanceColumnsForExcel(instance);

			if (instanceIndex == 0) {
				if (humanReadable) {
					form = instance.getForm();
					String fProperties = form.getProperties();
					if (jsonParserUtil.isValidArray(fProperties)) {
						formPropertiesJson = jsonParserUtil.parseAsArray(fProperties);
					}
				}
				// Creating Header Row for titles
				workSheet = renderColumnRow(workSheet, instanceColumns, instanceIndex, formPropertiesJson, lang);
			}

			Row bodyRow = workSheet.createRow(instanceIndex + 1);

			for (int i = 0; i < instanceColumns.size(); i++) {
				if (i == 0) {
					bodyRow.createCell(i).setCellValue(instance.getId());
				} else if (i == 1) {
					bodyRow.createCell(i).setCellValue(instance.getTitle());
				} else if (i == 2) {
					bodyRow.createCell(i)
							.setCellValue(instance.getCreatedAt() != null ? instance.getCreatedAt().toString() : "");
				} else if (i == 3) {
					bodyRow.createCell(i)
							.setCellValue(instance.getOwner() != null ? instance.getOwner().getName() : "");
				} else {
					Cell bodyCell = bodyRow.createCell(i);
					String value = properties.get(instanceColumns.get(i)).toString();
					bodyCell.setCellValue(value);
				}
			}

			instanceIndex++;
		}
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		return excelFile;
	}

	// import excel file to the instance
	public boolean excelImport(File excelFile, Form form, User user) throws IOException, ParserConfigurationException {
		FileInputStream inputStream = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet worksheet = workbook.getSheetAt(0);

		Instance blankInstance = getBlankInstance(form);
		blankInstance.setForm(form);
		blankInstance.setEnvSlug(userService.getCurrentEnv());
		blankInstance = instanceService.populateInstanceProperties(blankInstance);
		if (validateExcelImportTemplate(form, blankInstance, worksheet)) {
			List<Instance> instances = excelUtil.populateInstancesFromExcelSheet(worksheet, blankInstance, user);
			for (Instance instance : instances) {
				instanceService.create(instance);
			}
		}

		return true;
	}

	// This function get the blank instance columns list and compare it with excel
	// sheet columns
	public boolean validateExcelImportTemplate(Form form, Instance instance, XSSFSheet sheet) {
		if (instance != null) {
			List<String> instanceColumns = excelUtil.getInstanceColumnsForExcel(instance);
			List<String> excelColumns = excelUtil.getExcelColumns(sheet);
			if (instanceColumns.equals(excelColumns)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<File> getFiles(File directory, String[] fileExt, boolean recursive) {
		return (List<File>) FileUtils.listFiles(directory, fileExt, true);
	}

	@Override
	public List<String> getImages(Form form) {
		String[] extentionList = new String[] { "jpeg", "jpg", "png" };
		List<String> imagesList = new ArrayList<>();
		String instanceFormDirPath = uploadDir + File.separator + form.getEnvSlug() + "/instances/"
				+ form.getId().toString();

		File instanceFormDir = new File(instanceFormDirPath);
		if (instanceFormDir.exists()) {
			List<File> files = getFiles(instanceFormDir, extentionList, true);
			for (File file : files) {
				imagesList.add(file.getPath());
			}
		}

		return imagesList;
	}

	@Override
	public List<String> getImagesByEnv(Form form, String envSlug) {
		String[] extentionList = new String[] { "jpeg", "jpg", "png" };
		List<String> imagesList = new ArrayList<>();
		if (form.getEnvSlug().equals(envSlug)) {
			String instanceFormDirPath = uploadDir + File.separator + form.getEnvSlug() + "/instances/"
					+ form.getId().toString();

			List<File> files = getFiles(new File(instanceFormDirPath), extentionList, true);
			for (File file : files) {
				imagesList.add(file.getPath());
			}
		}
		return imagesList;
	}

	/**
	 * Return the total record
	 *
	 * @param envSlug
	 * @param groups
	 *
	 */

	private Long getTotalRecords(String envSlug, String groups) {
		Object obj;
		Query query;

		if (this.userService.isAdmin()) {
			query = this.entityManager
					.createNativeQuery("SELECT count(*) FROM form f WHERE f.env_slug='" + envSlug + "'");
			obj = query.getSingleResult();
		} else {
			query = this.entityManager.createNativeQuery(
					"SELECT count(distinct f.id) from form f inner join form_group fg on f.id=fg.form_id WHERE fg.group_id IN ("
							+ groups + ") AND f.env_slug='" + envSlug + "'");
			obj = query.getSingleResult();

		}

		return Long.valueOf(obj.toString());
	}
}
