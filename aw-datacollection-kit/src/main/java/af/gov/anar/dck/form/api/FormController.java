package af.gov.anar.dck.form.api;

import af.gov.anar.dck.common.auth.FormAuthService;
import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.report.model.Report;
import af.gov.anar.dck.systemregistry.model.SystemRegistry;
import af.gov.anar.dck.systemregistry.service.SystemRegistryService;
import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.GroupService;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.workflow.model.Workflow;
import af.gov.anar.dck.workflow.service.WorkflowService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import af.gov.anar.dck.common.auth.InstanceAuthService;
import af.gov.anar.dck.common.auth.ReportAuthService;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.common.service.*;
import af.gov.anar.dck.infrastructure.util.DateTimeUtil;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping({ "/api/forms" })
public class FormController {

    Logger logger = LoggerFactory.getLogger(FormController.class);

    @Value("${app.url}")
    private String appUrl;

    @Value("${app.upload.temp}")
    private String uploadTempDirPath;

    @Value("${spring.mail.to}")
    private String emailTo;

    @Autowired
    private FormAuthService formAuthService;

    @Autowired
    private FormService formService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SystemRegistryService systemRegistryService;

    @Autowired
    private ReportAuthService reportAuthService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private InstanceAuthService instanceAuthService;

    @Autowired
    private InstanceService instanceService;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
	private ExceptionUtil exceptionUtil;
    
    private String failureEmailSub = "500 - ASIMS Exception - User";

    ObjectMapper mapper = new ObjectMapper();

    @Loggable
    @PostMapping
    public boolean create(@Valid @RequestBody String formString, HttpServletRequest request)
            throws Exception {
        logger.info("Entry FormController>create() - POST");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Gson gson = new Gson();
            // JsonObject obj = (new JsonParser()).parse(formString).getAsJsonObject();
            // String childFormsIds = obj.get("childForms").getAsString();
            // System.out.println(">>> the form object as a string "+
            // obj.get("form").toString());
            Form form = gson.fromJson(formString, Form.class);
            form.setEnvSlug(userService.getCurrentEnv());
            System.out.println(">>> create a form:" + form.toString());
            Collection<Report> reports = form.getReports();
            formAuthService.create(form);
            return setFormsReports(form, reports);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    @Loggable
    @GetMapping(path = { "/{id}" })
    public ObjectNode findOne(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
        logger.info("Entry FormController>findOne() - GET");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form foundForm = formAuthService.findById(id);

            List<Group> allGroups = groupService.findAll();
            List<SystemRegistry> allMapLayers = systemRegistryService.findAll();
            List<Report> allReports = reportAuthService.findAllWithoutXMLContent();
            List<Workflow> allWorkflows = workflowService.findAllWithoutJSONContent();
            JsonNode formJson = mapper.convertValue(foundForm, JsonNode.class);
            JsonNode groupsJson = mapper.convertValue(foundForm.getGroups(), JsonNode.class);
            JsonNode mapLayersJson = mapper.convertValue(foundForm.getMapLayers(), JsonNode.class);
            JsonNode reportsJson = mapper.convertValue(foundForm.getReports(), JsonNode.class);
            ArrayNode allGroupsTree = mapper.valueToTree(allGroups);
            ArrayNode allMapLayersTree = mapper.valueToTree(allMapLayers);
            ArrayNode allWorkflowsTree = mapper.valueToTree(allWorkflows);
            ArrayNode allReportsTree = mapper.valueToTree(allReports);

            ObjectNode node = mapper.createObjectNode();

            node.set("form", formJson);
            node.set("groups", groupsJson);
            node.set("mapLayers", mapLayersJson);
            node.set("reports", reportsJson);
            node.set("allGroups", allGroupsTree);
            node.set("allMapLayers", allMapLayersTree);
            node.set("allWorkflows", allWorkflowsTree);
            node.set("allReports", allReportsTree);
            return node;
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @PutMapping(path = { "/{id}" })
    public boolean update(@PathVariable("id") Long id, @RequestBody String formString, HttpServletRequest request)
            throws Exception {
        logger.info("Entry FormController>update() - PUT");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Gson gson = new Gson();
            // JsonObject obj = (new JsonParser()).parse(formString).getAsJsonObject();
            // String childFormsIds = obj.get("childForms").getAsString();
            Form form = gson.fromJson(formString, Form.class);

            form.setEnvSlug(userService.getCurrentEnv());
            form.setCreatedAt(this.formAuthService.findById(id).getCreatedAt());
            Collection<Report> reports = form.getReports();

            formAuthService.update(id, form);
            return setFormsReports(form, reports);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    public boolean setFormsReports(Form form, Collection<Report> reports) {
        Collection<Report> oldReports = reportAuthService.findByFormId(form.getId());
        for (Report oldReport : oldReports) {
            oldReport.setForm(null);
            reportAuthService.update(oldReport.getId(), oldReport);
        }
        for (Report report : reports) {
            Report newReport = reportAuthService.findById(report.getId());
            newReport.setForm(form);
            reportAuthService.update(report.getId(), newReport);
        }
        return true;
    }

    @Loggable
    @PutMapping(path = { "/update-form-grid/{id}" })
    public boolean updateFormGrid(@PathVariable("id") Long id, @RequestBody String formGrid,
            HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return formAuthService.updateFormGrid(id, formGrid);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    @Loggable
    @GetMapping
    public List findAll(HttpServletRequest request) throws Exception {
        // return formService.findAll();
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            String envSlug = userService.getCurrentEnv();
            return formAuthService.findAllWithoutXMLContentByEnv(envSlug);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @GetMapping(path = { "/summary" })
    public List findAllWithInstanceCount(HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            String envSlug = userService.getCurrentEnv();
            return formAuthService.findAllWithInstanceCountByEnv(envSlug);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @PostMapping(path = { "/summary" })
    public DataTablesOutput<Form> findAllWithInstanceCount(@Valid @RequestBody DataTablesInput input,
            HttpServletRequest request) throws Exception {

        User currentLoggedInUser = userAuthService.getLoggedInUser();
        DataTablesOutput<Form> output = new DataTablesOutput<>();
        try {
            Object[] returnedObj = this.formAuthService.findAllWithInstanceCountByEnv(input);
            @SuppressWarnings("unchecked")
            List<Object[]> formsList = (List<Object[]>) returnedObj[0];
            Long total = Long.valueOf(returnedObj[1].toString());
            List<Form> forms = new ArrayList<>(formsList.size());

            for (Object[] obj : formsList) {
                Form form = new Form();
                form.setId(((BigInteger) obj[0]).longValue());
                if ((obj[1] != null)) {
                    String name = (String) obj[1].toString();
                    if (name != null & !"null".equalsIgnoreCase(name)) {
                        form.setName(name);
                    }

                }
                if (obj[2] != null) {
                    String description = (String) obj[2].toString();
                    if (description != null && !"null".equalsIgnoreCase(description)) {
                        form.setDescription(description);
                    }

                }
                if (obj[3] != null) {
                    String xmlFormId = (String) obj[3].toString();
                    if (xmlFormId != null && !"null".equalsIgnoreCase(xmlFormId)) {
                        form.setXmlFormId(xmlFormId);

                    }

                }
                if (obj[4] != null) {
                    String formType = (String) obj[4].toString();
                    if (formType != null && !"null".equalsIgnoreCase(formType)) {
                        form.setFormType(formType);
                    }
                }
                if (obj[5] != null) {
                    // String formCategory = (String) obj[5].toString();
                    int formCategory = (int) obj[5];
                    form.setFormCategory(formCategory);
                }
                if (obj[6] != null) {
                    boolean active = (Boolean) obj[6];
                    form.setActive(active);
                }
                if (obj[7] != null) {
                    Long count = ((BigInteger) obj[7]).longValue();
                    form.setInstanceCount(count);
                }
                forms.add(form);

                // forms.add(new Form(((BigInteger) form[0]).longValue(), (String) form[1],
                // (String) form[2],
                // (String) form[3], (String) form[4], (String) form[5], (Boolean) form[6],
                // ((BigInteger) form[7]).longValue()));
            }
            output.setDraw(input.getDraw());
            output.setRecordsFiltered(total);
            output.setData(forms);
            output.setRecordsTotal(total);

        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
        return output;

    }

    @Loggable
    @GetMapping(path = { "/{formId}/name" })
    public String getFormNameById(@PathVariable("formId") Long formId, HttpServletRequest request) throws Exception {
        // return instanceService.findAllByForm(formId);
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formAuthService.findById(formId);
            return form.getName();
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @GetMapping(path = { "/{formId}/map_layers" })
    public List findAllMapLayersOfForm(@PathVariable("formId") Long formId, HttpServletRequest request)
            throws Exception {
        // return instanceService.findAllByForm(formId);
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formAuthService.findById(formId);
            Collection<SystemRegistry> mapLayers = form.getMapLayers();
            return (List) mapLayers;
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @GetMapping(path = { "/{formId}/hasgeometry" })
    public boolean gethasGeometryById(@PathVariable("formId") Long formId, HttpServletRequest request)
            throws Exception {
        // return instanceService.findAllByForm(formId);
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formAuthService.findById(formId);
            System.out.println("----------------------------------------------");
            System.out.println(form.toString());
            System.out.println(form.isHasGeometry());
            return form.isHasGeometry() ? true : false;
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    // deprecated
    @Loggable
    @GetMapping(path = { "/{formId}/instances" })
    public List findAllInstancesOfForm(@PathVariable("formId") Long formId, HttpServletRequest request)
            throws Exception {
        // return instanceService.findAllByForm(formId);
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return instanceAuthService.findAllByFormWithoutXMLContent(formId);
        } catch (Exception e) {
            System.out.println("&&&" + e.toString());
            logger.debug(e.getMessage());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @PostMapping(path = { "/{formId}/instances" })
    public DataTablesOutput findAllInstancesOfForm(@PathVariable("formId") Long formId,
            @Valid @RequestBody DataTablesInput input, HttpServletRequest request) throws Exception {
        // return instanceService.findAllByForm(formId);

        User currentLoggedInUser = userAuthService.getLoggedInUser();

        List<Instance> instancesList = new ArrayList<Instance>();
        Long total = 0L;
        try {
            Object[] instancesAndTotalRecord = instanceAuthService.findAllByFormWithoutXMLContent(input, formId);
            @SuppressWarnings("unchecked")
            List<Object[]> instances = (List<Object[]>) instancesAndTotalRecord[0];
            total = Long.valueOf(instancesAndTotalRecord[1].toString());
            for (Object[] obj : instances) {
                Instance instance = new Instance();

                // Long id = ((BigInteger) obj[0]).longValue();
                Long id = Long.valueOf(obj[0].toString());

                instance.setId(id);
                if (obj[1] != null) {
                    // Integer version = ((Integer) obj[1]).intValue();
                    Integer version = Integer.valueOf(obj[1].toString());

                    if (version != null) {
                        instance.setVersion(version);
                    }

                }

                if (obj[2] != null) {
                    String title = obj[2].toString();

                    if (title != null && !"null".equalsIgnoreCase(title)) {
                        instance.setTitle(title);
                    } else {
                        instance.setTitle(" ");
                    }

                }

                if (obj[3] != null) {
                    String currentStep = obj[3].toString();
                    if (currentStep != null && !"null".equalsIgnoreCase(currentStep)) {
                        instance.setCurrentStep(currentStep);
                    } else {
                        instance.setCurrentStep(" ");
                    }
                }
                if (obj[4] != null) {
                    Timestamp dt = (Timestamp) obj[4];
                    if (dt != null) {
                        instance.setCreatedAt(dt.toLocalDateTime());
                    }
                }
                if (obj[5] != null) {
                    Timestamp updatedTime = (Timestamp) obj[5];
                    if (updatedTime != null) {
                        instance.setUpdatedAt(updatedTime.toLocalDateTime());
                    }
                }
                if (obj[6] != null) {
                    String owner = obj[6].toString();
                    User u = new User();
                    u.setName(owner);
                    instance.setOwner(u);
                }
                instancesList.add(instance);

            }
            // return instanceAuthService.findAllByFormWithoutXMLContent(formId);
        } catch (Exception e) {
            System.out.println(">>> exception is :" + e.toString());
            logger.debug(e.getMessage());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
        DataTablesOutput<Instance> output = new DataTablesOutput<Instance>();
        output.setDraw(input.getDraw());
        output.setData(instancesList);
        output.setRecordsFiltered(total);
        output.setRecordsTotal(total);
        return output;
    }

    @GetMapping(path = { "/formlayout/{formId}" })
    public String formlayout(String formId) {
        return formId;
    }

    @Loggable
    @GetMapping(path = { "/{formId}/reports" })
    public List findAllReportsOfForm(@PathVariable("formId") Long formId, HttpServletRequest request) throws Exception {
        // return instanceService.findAllByForm(formId);
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return reportAuthService.findAllByFormWithoutXMLContent(formId);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @GetMapping(path = { "/instances" })
    public List findAllInstances(HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return instanceAuthService.getChildInstancesWithoutXMLContent(null);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @PostMapping(path = { "/instances" })
    public DataTablesOutput<Instance> findAllInstances(@Valid @RequestBody DataTablesInput input,
            HttpServletRequest request) throws Exception {
        DataTablesOutput<Instance> output = new DataTablesOutput<Instance>();
        List<Instance> instancesList = new ArrayList<Instance>();

        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Object[] obj = instanceAuthService.getChildInstancesWithoutXMLContent(input, null);
            List<Object[]> instances = (List<Object[]>) obj[0];
            Long total = Long.valueOf(obj[1].toString());
            for (Object[] instance : instances) {
                Instance newInstance = new Instance();
                // newInstance.setId(((BigInteger) instance[0]).longValue());
                newInstance.setId(Long.valueOf(instance[0].toString()));

                if (instance[1] != null) {
                    // Integer version = ((BigInteger) obj[1]).intValue();
                    Integer version = Integer.valueOf(obj[1].toString());
                    if (version != null) {
                        newInstance.setVersion(version);
                    }

                }
                if (instance[2] != null) {
                    String title = instance[2].toString();
                    if (title != null) {
                        newInstance.setTitle(title);
                    }
                }
                if (instance[3] != null) {
                    String currentStep = instance[3].toString();
                    if (currentStep != null) {
                        newInstance.setCurrentStep(currentStep);
                    }
                }
                if (instance[4] != null) {
                    Timestamp createdDate = (Timestamp) instance[4];
                    if (createdDate != null) {
                        newInstance.setCreatedAt(createdDate.toLocalDateTime());
                    }

                }
                if (instance[5] != null) {
                    Timestamp updatedDate = (Timestamp) instance[5];
                    if (updatedDate != null) {
                        newInstance.setUpdatedAt(updatedDate.toLocalDateTime());
                    }
                }
                if (instance[6] != null) {
                    String ownerName = instance[6].toString();
                    if (ownerName != null && !"null".equalsIgnoreCase(ownerName)) {
                        User newUser = new User();
                        newUser.setName(ownerName);
                        newInstance.setOwner(newUser);
                    }
                }
                System.out.println(">>>> new instance:" + newInstance.toString());
                instancesList.add(newInstance);

            }
            output.setDraw(input.getDraw());
            output.setData(instancesList);
            output.setRecordsFiltered(total);
            output.setRecordsTotal(total);
        } catch (Exception e) {
            System.out.println(">>>>>> Exception is:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
        return output;
    }

    @Loggable
    @GetMapping(path = { "/instances/{parentInstanceId}" })
    public List findInstancesByParent(@PathVariable("parentInstanceId") Long parentInstanceId,
            HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return new ArrayList();
            // return
            // instanceAuthService.getChildInstancesWithoutXMLContent(parentInstanceId);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @RequestMapping(value = "/{id}/xml", method = RequestMethod.GET)
    public String getFormContent(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return formAuthService.findById(id).getXmlContent();
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @GetMapping(path = { "/child_forms/{formId}" })
    public List findByParentFormIdWithoutXMLContent(@PathVariable("formId") Long formId, HttpServletRequest request)
            throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return this.formAuthService.findByParentFormIdWithoutXMLContent(formId);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return formAuthService.delete(id);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    // @GetMapping(value = "/{formId}/jasper_xml_datasource", produces = {
    // "application/xml", "text/xml" })
    @GetMapping(value = "/{formId}/jasper_xml_datasource")
    public String getBlankInstanceXmlContentWithHeadersAsJasperDataSource(@PathVariable("formId") Long formId,
            HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formAuthService.findById(formId);
            // TODO the child instances should also be added to main child
            Instance instance = formAuthService.getBlankInstanceXmlContentWithHeaders(form);
            System.out.println(instance.getXmlContent());
            return instance.getXmlContent();
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    // excel import excel file to fomrs
    @PostMapping(value = "/{formId}/excel_import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean excelImport(@RequestParam("file") MultipartFile multipart, @PathVariable("formId") Long formId,
            HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            File excelFile = File.createTempFile("excel_import_" + multipart.getOriginalFilename(), ".xlsx");

            FileOutputStream fos = new FileOutputStream(excelFile);
            fos.write(multipart.getBytes());
            fos.close();

            System.out.println("multipart's original name: " + multipart.getOriginalFilename());

            Form form = formAuthService.findById(formId);
            User user = userService.getLoggedInUser();

            formAuthService.excelImport(excelFile, form, user);

            return true;
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    // convert excel file into xml
    public Boolean convertExcelFileToXML(XSSFSheet worksheet) throws ParserConfigurationException {
        for (Row row : worksheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    System.out.print(">>>>>>>>" + cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.print(">>>>>>>>" + cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.print(">>>>>>>>" + cell.getNumericCellValue());
                    break;
                }
                System.out.print("\t");
            }
            System.out.println();
        }

        return true;
    }

    // excel export and xml parsing
    @GetMapping("/{formId}/excel_export")
    public void createExcel(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("formId") Long formId, @RequestParam("lang") String lang,
            @RequestParam("human_readable") boolean humanReadable) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            List instances = instanceService.findAllByForm(formId);
            instances = instanceService.populateInstanceProperties(instances);
            File excelFile = formAuthService.excelExport(instances, lang, humanReadable);
            if (excelFile.exists()) {
                System.out.println("File Created");
            }

            File uploadTempDir = new File(uploadTempDirPath + "/" + userAuthService.getCurrentEnv());
            FileUtils.moveFileToDirectory(excelFile, uploadTempDir, true);

            String messageStr = "From this link you can download your excel export file.";
            messageStr += "<a href='" + appUrl + "api/files/download/temp?file=" + excelFile.getName()
                    + "' target='_blank'>Download</a> ";
            messageStr += excelFile.getName();

            emailUtil.sendMessageWithDetails(emailTo, "200 - ASIMS Excel Export - Form", messageStr, null,
                    currentLoggedInUser, userService.getCurrentEnv(), request);

            filedownload(excelFile, response);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return;
        }
    }

    // excel template
    @GetMapping("/{formId}/excel_template")
    public void createExcelImportTemplate(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("formId") Long formId) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formAuthService.findById(formId);
            Instance instance = formAuthService.getBlankInstance(form);
            instance = instanceService.populateInstanceProperties(instance);

            File excelFile = formAuthService.getExcelImportTemplate(instance);
            if (excelFile.exists()) {
                System.out.println("File Created");
            }
            filedownload(excelFile, response);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return;
        }
    }

    private void filedownload(File file, HttpServletResponse response) {
        final int BUFFER_SIZE = 4096;
        String fullPath = file.getAbsolutePath();
        String fileName = file.getName();
        if (file.exists()) {
            try {
                Path path = file.toPath();
                String mimeType = Files.probeContentType(path);
                FileInputStream inputStream = new FileInputStream(file);
                // String mimeType = context.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                // file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Loggable
    @GetMapping(value = { "/{formId}/gallary" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<String> getGallaryImages(@PathVariable(value = "formId", required = true) Long formId,
            HttpServletRequest request) throws Exception {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formService.findById(formId);
            String envSlug = userService.getCurrentEnv();
            return formService.getImagesByEnv(form, envSlug);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

}