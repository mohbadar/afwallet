package af.gov.anar.dck.instance.service;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;
import af.gov.anar.dck.instance.model.InstanceComment;
import af.gov.anar.dck.instance.model.InstanceHistory;
import af.gov.anar.dck.instance.repository.InstanceCommentRepository;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

import af.gov.anar.dck.infrastructure.util.XmlParserUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.repository.FormRepository;
import af.gov.anar.dck.instance.repository.InstanceRepository;
import af.gov.anar.dck.report.model.Report;
import af.gov.anar.dck.report.service.ReportService;
import af.gov.anar.dck.infrastructure.util.DateTimeUtil;
import af.gov.anar.dck.infrastructure.util.JsonParserUtil;
import net.sf.jasperreports.engine.JRException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//import com.springboot.pdfexcel.model.instances;

@Service
public class InstanceServiceImpl implements InstanceService {

    private final Path fileStorageLocation;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.upload.temp}")
    private String tempDir;

    @Autowired
    private InstanceRepository instanceRepository;

    @Autowired
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private InstanceHistoryService instanceHistoryService;

    @Autowired
    private InstanceCommentService instanceCommentService;

    @Autowired
    private InstanceTransitionService instanceTransitionService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private InstanceWatcherService instanceWatcherService;
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private XmlParserUtil xmlParserUtil;

    @Autowired
    private JsonParserUtil jsonParserUtil;

    @Autowired
    private DateTimeUtil dateTimeUtil;
    
    ObjectMapper mapper = new ObjectMapper();

    InstanceServiceImpl() {
        this.fileStorageLocation = Paths.get("").toAbsolutePath().normalize();
    }

    @Override
    public Long getCountByFormId(Long formId) {
        return instanceRepository.countByFormId(formId);
    }

    @Override
    public List<Instance> create_batch(List<Instance> instances) {
        return instanceRepository.saveAll(instances);
    }

    @Override
    public Instance create(Instance instance) {
        return instanceRepository.save(instance);
    }

    @Override
    public Instance create(Long formId) {
        return create(formId, userService.getLoggedInUser());
    }

    @Override
    public Instance create(Long formId, Long parentInstanceId, String formxmlContainer) {
        return create(formId, userService.getLoggedInUser(), parentInstanceId, formxmlContainer);
    }

    @Override
    public Instance create(Long formId, User user) {
        return create(formId, user, null, null);
    }

    @Override
    public Instance create(Long formId, User user, Long parentInstanceId, String formXmlTitle) {
        Form form = formRepository.getOne(formId);
        String xmlString = form.getXmlContent();
        if (xmlParserUtil.isValid(xmlString)) {
            Document doc = xmlParserUtil.parse(xmlString);
            Document targetSubTreeDoc = xmlParserUtil.getXFormInstanceNodeTree(doc);

            Instance instance = new Instance();
            instance.setXmlContent(xmlParserUtil.convertToString(targetSubTreeDoc));
            instance.setForm(formRepository.getOne(formId));
            instance.setOwner(user);
            instance.setEnvSlug(userService.getCurrentEnv());
            instance.setActive(true);
            instance.setFirstUpdate(true);
            if (parentInstanceId != null) {
                Instance parentInsatnce = findById(parentInstanceId);
                instance.setParentInstance(parentInsatnce);
            }
            if (formXmlTitle != null) {
                instance.setTitle(formXmlTitle);
            }

            create(instance);

            // creates the directory for instance
            instance = createInstanceDirectory(instance);
            return instance;
        }
        return null;
    }

    @Override
    public Instance cloneInstance(Long instanceId, User loggedInUser) {
        Instance instance = findById(instanceId);
        Instance cloneInstance = new Instance(instance);
        cloneInstance.setCurrentStep("");
        cloneInstance.setResolution("");
        cloneInstance.setOwner(loggedInUser);
        cloneInstance.setFirstUpdate(false);
        cloneInstance.setCreatedAt(dateTimeUtil.getCurrentDate());
        cloneInstance.setUpdatedAt(dateTimeUtil.getCurrentDate());
        return instanceRepository.save(cloneInstance);
    }

    @Override
    public String getBlankInstance(Long formId) {
        Form form = formRepository.getOne(formId);
        String gridJson = form.getGridJson();
        String xmlString = form.getXmlContent();
        if (xmlParserUtil.isValid(xmlString)) {
            Document doc = xmlParserUtil.parse(xmlString);
            Document targetSubTreeDoc = xmlParserUtil.getXFormInstanceNodeTree(doc);
            String blankInstance = xmlParserUtil.convertToString(targetSubTreeDoc);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("instanceXml", blankInstance);
            jsonObject.put("formXml", xmlParserUtil.convertToString(doc));

            jsonObject.put("gridJson", gridJson);

            return jsonObject.toString();
        }

        return null;
    }

    // @Override
    // public Instance delete(Long id) {
    // Instance instance = findById(id);
    // if(instance != null){
    // instanceRepository.delete(instance);
    // }
    // return instance;
    // }

    @Override
    public boolean delete(Long id) {
        Instance instance = findById(id);
        if (instance != null) {
            instanceRepository.delete(instance);
            deleteInstanceHistory(instance.getId());
            deleteInstanceComments(instance.getId());
            deleteInstanceTransitions(instance.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteInstanceHistory(Long instanceId) {
        instanceHistoryService.deleteByInstance(instanceId);
        return true;
    }

    @Override
    public boolean deleteInstanceComments(Long instanceId) {
        instanceCommentService.deleteByInstance(instanceId);
        return true;
    }
    
    @Override
    public boolean deleteInstanceTransitions(Long instanceId) {
        instanceTransitionService.deleteByInstance(instanceId);
        return true;
    }

    @Override
    public List<Instance> findByFormIdAndRowId(Long formId, String rowId) {
        return instanceRepository.findByFormIdAndRowId(formId, rowId);
    }

    @Override
    public List findAllByForm(Long formId) {
        // Collection<Instance> instances =
        // formRepository.getOne(formId).getInstances();
        Collection<Instance> instances = instanceRepository.findByFormId(formId);
        return new ArrayList<>(instances);
    }

    @Override
    public List findAllByFormWithoutXMLContent(Long formId) {
        Collection<Instance> instances = instanceRepository.findByFormIdWithoutXMLContent(formId);
        return new ArrayList<>(instances);
    }

    @Override
    public Object[] findAllByFormWithoutXMLContent(DataTablesInput input, Long formId) {

        Object[] instancesAndLength = new Object[2];
        Query query;
        int start = input.getStart();
        int limit = input.getLength();
        // if limit is -1 it means it should return all the records
        if (limit < 0) {
            limit = 99999;
        }
        List<Object[]> instancesObjects = new ArrayList<>();
        Long totalRecord = 0L;
        String envSlug = userService.getCurrentEnv();
        if (hasAuthority(new SimpleGrantedAuthority("ADMIN"))
                || hasAuthority(new SimpleGrantedAuthority("INSTANCE_LIST_ ALL"))) {
            String regex = input.getSearch().getValue();
            if (regex != null && !"".equalsIgnoreCase(regex)) {
                query = this.entityManager.createNativeQuery(
                        "SELECT i.id, i.version, i.title, i.current_step, i.created_at, i.updated_at, ut.name AS created_by FROM instance i LEFT JOIN user_tbl ut ON i.owner_id=ut.id WHERE i.form_id="
                                + formId + " AND (i.title ILIKE '%" + regex + "%' OR i.current_step ILIKE '%" + regex
                                + "' OR ut.name ILIKE '%" + regex + "%') limit " + limit + " offset " + start);

            } else {
                query = this.entityManager.createNativeQuery(
                        "SELECT i.id, i.version, i.title, i.current_step, i.created_at, i.updated_at, ut.name AS created_by FROM instance i LEFT JOIN user_tbl ut ON i.owner_id=ut.id WHERE i.form_id="
                                + formId + " limit " + limit + " offset " + start);

            }

            instancesObjects = query.getResultList();
            totalRecord = getTotalRecord(envSlug, null, formId);
        } else {
            Long id = userService.getLoggedInUser().getId();
            if (id != null) {
                query = this.entityManager.createNativeQuery(
                        "SELECT i.id, i.version, i.title, i.current_step, i.created_at, i.updated_at, ut.name AS created_by FROM instance i LEFT JOIN user_tbl ut ON i.owner_id=ut.id WHERE i.owner_id ="
                                + id + " AND i.form_id=" + formId + " AND i.env_slug = '" + envSlug + "' limit " + limit
                                + " offset " + start);
                instancesObjects = query.getResultList();
                totalRecord = getTotalRecord(envSlug, id, formId);
            }
        }
        // Collection<Instance> instances =
        // instanceRepository.findByFormIdWithoutXMLContent(formId);
        instancesAndLength[0] = instancesObjects;
        instancesAndLength[1] = totalRecord;
        return instancesAndLength;
    }

    @Override
    public List<Instance> getChildInstancesWithoutXMLContent(Long parentInstanceId) {
        if (parentInstanceId != null) {
            return instanceRepository.getChildInstancesWithoutXMLContent(parentInstanceId);
        } else {
            return instanceRepository.getAllInstancesWithoutXMLContent();
        }
    }

    @Override
    public Object[] getChildInstancesWithoutXMLContent(DataTablesInput input, Long parentInstanceId) {
        String envSlug = userService.getCurrentEnv();
        int start = input.getStart();
        int limit = input.getLength();
        if (limit < 0) {
            limit = 99999;
        }
        Object[] instancesAndLength = new Object[2];
        List<Object[]> instancesObjects = new ArrayList<>();
        Query query;
        Long userId = userService.getLoggedInUser().getId();

        if (parentInstanceId != null) {

            query = this.entityManager.createNativeQuery(
                    "SELECT id, version, title, current_step, created_at, updated_at FROM instance where instance.parent_instance_id = "
                            + parentInstanceId);
            instancesObjects = query.getResultList();

            // instanceRepository.getChildInstancesWithoutXMLContent(parentInstanceId);
        } else {
            String regex = input.getSearch().getValue();

            if (userService.isAdmin() || hasAuthority(new SimpleGrantedAuthority("INSTANCES_LIST"))) {
                if (regex != null && !"".equals(regex)) {

                    query = this.entityManager.createNativeQuery(
                            "SELECT i.id, i.version, i.title, i.current_step, i.created_at, i.updated_at, ut.name from instance i left join user_tbl ut on i.owner_id=ut.id WHERE i.env_slug='"
                                    + envSlug + "' AND ( i.title ILIKE '%" + regex + "%' OR ut.name ILIKE '%" + regex
                                    + "%'OR i.current_step ILIKE '%" + regex + "%') group by i.id, ut.name limit "
                                    + limit + " offset " + start);
                } else {
                    query = this.entityManager.createNativeQuery(
                            "SELECT i.id, i.version, i.title, i.current_step, i.created_at, i.updated_at, ut.name from instance i left join user_tbl ut on i.owner_id=ut.id WHERE i.env_slug='"
                                    + envSlug + "' limit " + limit + " offset " + start);

                }

            } else {
                if (regex != null && !"".equals(regex)) {
                    query = this.entityManager.createNativeQuery(
                            "SELECT i.id, i.version, i.title, i.current_step, i.created_at, i.updated_at, ut.name from instance i left join user_tbl ut on i.owner_id=ut.id WHERE i.env_slug='"
                                    + envSlug + "' AND i.owner_id = " + userId + " AND (i.title ILIKE '%" + regex
                                    + "%' OR i.current_step ILIKE '%" + regex + "%') group by i.id, ut.name limit "
                                    + limit + " offset " + start);

                } else {
                    query = this.entityManager.createNativeQuery(
                            "SELECT i.id, i.version, i.title, i.current_step, i.created_at, i.updated_at, ut.name from instance i left join user_tbl ut on i.owner_id=ut.id WHERE i.env_slug='"
                                    + envSlug + "' AND i.owner_id = " + userId + " limit " + limit + " offset "
                                    + start);

                }

            }

            instancesObjects = query.getResultList();

            // return instanceRepository.getAllInstancesWithoutXMLContent();
        }
        instancesAndLength[0] = instancesObjects;
        instancesAndLength[1] = getTotalRecord(envSlug, userId, null);
        return instancesAndLength;
    }

    @Override
    public List<Instance> getChildInstances(Long instanceId) {
        return instanceRepository.getChildInstances(instanceId);
    }

    @Override
    public Instance addImage(Instance instance, String fieldName, File savedImageFile, boolean isRepeatField) {
        Instance newInstance = xmlParserUtil.addImageToInstanceXmlContent(instance, fieldName, savedImageFile,
                isRepeatField);
        return instanceRepository.save(newInstance);
    }

    @Override
    public Instance findById(Long id) {
        Optional<Instance> optionalObj = instanceRepository.findById(id);
        if(optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public boolean update(Long id, String pl, boolean notifyWatchers) {
        JSONObject jsObj = jsonParserUtil.parse(pl);
        String payload = jsObj.getString("payload");
        String title = "";
        if (!jsObj.isNull("title")) {
            title = jsObj.getString("title");
        }

        System.out.println("Title: " + title);
        if (id != null) {
            Instance instance = findById(id);
            String oldTitle = "";
            if (instance.getTitle() != null && !"null".equalsIgnoreCase(instance.getTitle())) {
                oldTitle = instance.getTitle().toString();

            }

            String instanceXmlContent = instance.getXmlContent();
            String formXmlContent = formRepository.getOne(instance.getForm().getId()).getXmlContent();

            System.out.println(instanceXmlContent);

            if (jsonParserUtil.isValid(payload) && xmlParserUtil.isValid(instanceXmlContent)) {
                JSONObject jsonObj = jsonParserUtil.parse(payload);
                boolean isFirstUpdate = instance.isFirstUpdate();
                Document instanceXmlObj = xmlParserUtil.parse(instanceXmlContent);
                Document formXmlObj = xmlParserUtil.parse(formXmlContent);
                User loggedInUser = userService.getLoggedInUser();
                Document updatedXmlObj = xmlParserUtil.updateInstanceXmlbyJson(instanceXmlObj, formXmlObj, jsonObj, id,
                        loggedInUser, isFirstUpdate, false, notifyWatchers);

                instance.setXmlContent(xmlParserUtil.convertToString(updatedXmlObj));
                instance.setEnvSlug(userService.getCurrentEnv());
                instance.setTitle(title);
                instance.setFirstUpdate(false);
                instanceRepository.save(instance);
                // Adding title to history
                addTitleToHistory(oldTitle, instance, title, notifyWatchers);
                return true;
            }
        }
        return false;
    }

    public void addTitleToHistory(String oldTitle, Instance instance, String newTitle, boolean notifyWatchers) {
        InstanceHistory instanceHistory = new InstanceHistory();
        instanceHistory.setField("Instance Title");
        instanceHistory.setInstanceId(instance.getId());
        instanceHistory.setOldValue(oldTitle);
        instanceHistory.setNewValue(newTitle);
        instanceHistory.setUserId(userService.getLoggedInUser().getId());
        // Saving history
        InstanceHistory addedHisotry = instanceHistoryService.create(instanceHistory);
        if (notifyWatchers) {
            // Making array List, we need for sending email
            List<InstanceHistory> instanceHistores = new ArrayList<>();
            instanceHistores.add(addedHisotry);
            // Sending email
            instanceWatcherService.sendEmail(instance.getId(), instanceHistores);
        }
    }

    @Override
    public boolean update(Long id, Instance instance, String envSlug) {
        System.out.println("Entry to update <Long, Instance>");
        if (id != null) {
            instance.setId(id);
        }
        if (id != null) {
            instance.setEnvSlug(envSlug);
        }
        instanceRepository.save(instance);
        return true;
    }

    @Override
    public boolean detach(Long id) {
        Instance instance = findById(id);
        instance.setParentInstance(null);
        String envSlug = userService.getCurrentEnv();
        return update(id, instance, envSlug);
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws IOException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new IOException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new IOException("File not found " + fileName, ex);
        }
    }

    @Override
    public String getInstanceFolderPath(Long instanceId) throws IOException {
        Instance instance = findById(instanceId);
        return getInstanceFolderPath(instance);
    }

    @Override
    public String getInstanceFolderPath(Instance instance) throws IOException {
        String instanceFolderPath = instance.getInstanceFolderName();
        if (instanceFolderPath != null && !instanceFolderPath.equals("")) {
            return instanceFolderPath;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String envSlug = instance.getEnvSlug();
        Form form = instance.getForm();
        instanceFolderPath = uploadDir + "/" + envSlug + "/instances/" + form.getId() + "/" + instance.getId() + "/"
                + form.getXmlFormId() + "_" + timestamp.getTime();
        File folder = new File(instanceFolderPath);
        Files.createDirectories(folder.toPath());
        instance.setInstanceFolderName(instanceFolderPath);
        update(instance.getId(), instance, envSlug);
        return instanceFolderPath;
    }

    // public File getInstanceDirectory(Instance instance) throws IOException {
    // String instanceFolder = instance.getInstanceFolderxmlContainer();
    // File dir;
    // if(instanceFolder != null && instanceFolder.length() > 0) {
    // dir = new File(getEnvironmenDirectory(env), formFolderxmlContainer);
    // } else {

    // // If there is no directory then we are creating a directory
    // dir = new File(getEnvironmenDirectory(env), formFolderxmlContainer);
    // }
    // return dir;
    // }

    @Override
    public List populateInstanceProperties(List<Instance> instances) {
        for (Instance instance : instances) {
            instance = populateInstanceProperties(instance);
            System.out.println("Properties updated");
        }
        return instances;
    }

    @Override
    public Instance populateInstanceProperties(Instance instance) {
        return xmlParserUtil.setInstancePropertiesByXmlContent(instance);
    }

    public File generatePdfJasperReport(Long instanceId, Long reportId) throws IOException, JRException {
        Report report = reportService.findById(reportId);

        File tempJrxmlFile = File.createTempFile("jasper_template", ".jrxml");
        // BufferedWriter bw = new BufferedWriter(new FileWriter(tempJrxmlFile));
        // bw.write(report.getXmlContent());
        // bw.close();
        Writer bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempJrxmlFile), "UTF-8"));
        try {
            bw.write(report.getXmlContent());
        } finally {
            bw.close();
        }

        Instance instance = findById(instanceId);
        // instance = populateInstanceProperties(instance);

        // Map<String, Object> properties = instance.getProperties();
        // List<Map<String, Object>> sourceProperties = new ArrayList<Map<String,
        // Object>>();
        // sourceProperties.add(properties);

        // // Add parameters

        // Map<String, Object> parameters = new HashMap<>();
        // parameters.put("createdBy", "Websparrow.org");

        File tempDestFile = File.createTempFile("jasper_template_pdf", ".pdf");

        File dir = new File(tempDir);
        if (!dir.exists())
            dir.mkdirs();

        File tempXMLFile = File.createTempFile("XMLDatasource", ".xml", new File(tempDir));

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempXMLFile), "UTF-8"));
        try {
            out.write(instance.getXmlContent());
        } finally {
            out.close();
        }

        // System.out.println("File path: " + tempXMLFile.getAbsolutePath());
        // BufferedWriter bufferedWriter = new BufferedWriter(new
        // FileWriter(tempXMLFile));
        // bufferedWriter.write(instance.getXmlContent());
        // bufferedWriter.close();

        return reportService.generatePdfJasperReport(tempXMLFile, null, tempJrxmlFile, tempDestFile);
    }

    @Override
    public long countByEnvSlug(String envSlug) {
        return instanceRepository.countByEnvSlug(envSlug);
    }

    @Override
    public long countByEnvSlugAndActive(String envSlug, Boolean active) {
        return instanceRepository.countByEnvSlugAndActive(envSlug, active);
    }

    @Override
    public Instance createInstanceDirectory(Instance instance) {
        if (instance.getInstanceFolderName() == null || instance.getInstanceFolderName() == "") {
            Form form = instance.getForm();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String instanceDirPath = uploadDir + File.separator + instance.getEnvSlug() + "/instances/"
                    + form.getId().toString() + File.separator + instance.getId().toString() + File.separator
                    + form.getXmlFormId() + "_" + timestamp.getTime();

            File dir = new File(instanceDirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            instance.setInstanceFolderName(instanceDirPath);

            return instance;
        }
        return null;
    }

    @Override
    public Instance deleteImage(Instance instance, String imageName, String fieldName, boolean isRepeatField) {
        Instance newInstance = xmlParserUtil.deleteImageTextNode(instance, imageName, fieldName, isRepeatField);
        return instanceRepository.save(newInstance);
    }

    public List<Instance> getInstancesExistFromList(Long formId, List<String> rowIds) {
        return instanceRepository.getInstancesExistFromList(formId, rowIds);
    }

    private boolean hasAuthority(SimpleGrantedAuthority auth) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> auths = ((UserDetails) principal).getAuthorities();
        if (auths.contains(auth)) {
            return true;
        }
        return false;
    }

    private long getTotalRecord(String envSlug, Long userId, Long formId) {
        Query query;
        Object obj;
        if (this.userService.isAdmin() || hasAuthority(new SimpleGrantedAuthority("INSTANCE_LIST_ ALL"))) {
            if (formId != null && !"null".equalsIgnoreCase(formId.toString())) {
                query = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM instance i WHERE i.env_slug = '"
                        + envSlug + "' AND i.form_id = " + formId);

            } else {
                query = this.entityManager
                        .createNativeQuery("SELECT COUNT(*) from instance i WHERE i.env_slug = '" + envSlug + "'");

            }
            obj = query.getSingleResult();

        } else {
            if (formId != null && !"null".equalsIgnoreCase(formId.toString())) {
                query = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM instance i WHERE i.form_id ="
                        + formId + " AND i.owner_id =  " + userId + " AND i.env_slug='" + envSlug + "'");

            } else {
                query = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM instance i WHERE i.owner_id = "
                        + userId + " AND i.env_slug = '" + envSlug + "'");
            }
            obj = query.getSingleResult();

        }
        return Long.valueOf(obj.toString());

    }

    @Override
    public List<Instance> hasChildren() {
        return instanceRepository.hasChildren();
    }

   
}
