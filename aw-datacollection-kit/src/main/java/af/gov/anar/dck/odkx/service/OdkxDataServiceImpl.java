package af.gov.anar.dck.odkx.service;


import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import af.gov.anar.dck.infrastructure.util.JsonParserUtil;
import af.gov.anar.dck.infrastructure.util.XmlParserUtil;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.util.*;

@Service
public class OdkxDataServiceImpl implements OdkxDataService {
    Logger logger = LoggerFactory.getLogger(OdkxDataServiceImpl.class);

    @Value("${spring.mail.to}")
    private String emailTo;

    @Value("${odkx.syncEndpoint.odkxURL}")
    private String odkxURL;

    @Value("${odkx.syncEndpoint.curlDefault}")
    private String curlDefault;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private FormService formService;

    @Autowired
    private UserService userService;

    @Autowired
    private InstanceService instanceService;

    @Autowired
    private XmlParserUtil xmlParserUtil;

    @Autowired
    private JsonParserUtil jsonParserUtil;

    private final int IDS_LIMIT = 30000;

    public InputStream executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        return process.getInputStream();
    }

    @Override
    public String syncFormData(Form form, User loggedInUser, String syncType, String rowsStr)
            throws ParserConfigurationException, ConstraintViolationException, InvalidObjectException {
        // System.out.println(rowsStr);
        String messageStr = "";
        form = formService.findById(form.getId());

        if (jsonParserUtil.isValid(rowsStr)) {
            JSONObject rowsObj = jsonParserUtil.parse(rowsStr);

            List<Instance> instances = new ArrayList<>();
            List<String> instancesRowIds = new ArrayList<>();

            String rootNodeXPath = "/" + form.getXmlFormId() + "/";

            JSONArray rowsArray = rowsObj.getJSONArray("rows");
            messageStr += "Total ODK Sync endpoint Records: " + rowsArray.length() + "<br>";
            for (int rowIndex = 0; rowIndex < rowsArray.length(); rowIndex++) {
                JSONObject row = rowsArray.getJSONObject(rowIndex);

                Instance newInstance = new Instance();

                String id = row.getString("id");

                newInstance.setRowId(id);
                newInstance.setOwner(loggedInUser);
                newInstance.setActive(true);
                newInstance.setForm(form);
                newInstance.setTitle(row.getString("createUser"));
                newInstance.setRowETag(row.getString("rowETag"));
                newInstance.setCurrentStep(row.getString("savepointType"));

                newInstance.setProperty(rootNodeXPath + "deleted", row.getBoolean("deleted"));
                newInstance.setProperty(rootNodeXPath + "createUser", row.getString("createUser"));
                newInstance.setProperty(rootNodeXPath + "lastUpdateUser", row.getString("lastUpdateUser"));
                newInstance.setProperty(rootNodeXPath + "savepointTimestamp", row.getString("savepointTimestamp"));
                JSONArray orderedColumns = row.getJSONArray("orderedColumns");

                // System.out.println(orderedColumns.toString());
                for (int colIndex = 0; colIndex < orderedColumns.length(); colIndex++) {
                    JSONObject columnObj = orderedColumns.getJSONObject(colIndex);

                    String column = columnObj.getString("column");
                    Object valueObj = columnObj.get("value");
                    String value = "";
                    if (valueObj != null) {
                        value = valueObj.toString();
                    }

                    // System.out.println("col: " + column + " val: " + value);
                    newInstance.setProperty(rootNodeXPath + column, value);
                }

                // System.out.println("rowId: " + id);
                instancesRowIds.add(id);
                instances.add(newInstance);
            }

            // These instance should be updated
            List<Instance> instancesExistInDB = new ArrayList<Instance>();
            
            int LOWER_LIMIT = 0;
            int UPPER_LIMIT = IDS_LIMIT;
            int REMAINING_RECORDS = 0;

            // due to variable limit, we must check a limited chunk of IDs per iteration
            if (instancesRowIds.size() > IDS_LIMIT) {
                
                while (UPPER_LIMIT <= instancesRowIds.size()) {
                    if(LOWER_LIMIT == UPPER_LIMIT) {
                       break; 
                    }
                    List<String> subInstanceRowIds = new ArrayList<String>();
                    subInstanceRowIds = instancesRowIds.subList(LOWER_LIMIT, UPPER_LIMIT);

                    instancesExistInDB.addAll(instanceService.getInstancesExistFromList(form.getId(),subInstanceRowIds));
                    
                    LOWER_LIMIT = UPPER_LIMIT;
                    REMAINING_RECORDS = instancesRowIds.size() - UPPER_LIMIT;

                    if(REMAINING_RECORDS > IDS_LIMIT) {
                        UPPER_LIMIT += IDS_LIMIT;
                    } else {
                        UPPER_LIMIT += REMAINING_RECORDS;
                    }
                    logger.debug("Lower Limit: " + LOWER_LIMIT + " Upper Limit: " + UPPER_LIMIT + " Remaining Records: " + REMAINING_RECORDS);
                }

            } else {
                instancesExistInDB.addAll(instanceService.getInstancesExistFromList(form.getId(),instancesRowIds));
            }



            Map<String, String> instanceIdsExistInDB = new HashMap<>();
            for (Instance i : instancesExistInDB) {
                instanceIdsExistInDB.put(i.getRowId(), i.getRowETag());
            }
            Set<String> instanceIdsExistInDB_rowId = instanceIdsExistInDB.keySet();

            User userMadeChanges = loggedInUser;
            List<Instance> instancesToBeInserted = new ArrayList<>();
            List<Instance> instancesToBeUpdated = new ArrayList<>();
            for (Instance instance : instances) {
                String rowId = instance.getRowId();

                if (instanceIdsExistInDB_rowId.contains(rowId)) {
                    // UPDATE
                    String rowETag = instanceIdsExistInDB.get(rowId);

                    // if _rowETag is same as instance rowETag then it means the instance is not
                    // updated
                    if (!rowETag.equals(instance.getRowETag())) {
                        List<Instance> instanceToBeUpdatedList = instanceService.findByFormIdAndRowId(form.getId(),
                                instance.getRowId());
                        // It should return only one, if it is more than one, it mean rowId is not
                        // unique
                        if (instanceToBeUpdatedList.size() > 1) {
                            throw new ConstraintViolationException("Duplicate RowId exists in database.", null, null);
                        }

                        Instance instanceToBeUpdated = instanceToBeUpdatedList.get(0);

                        instanceToBeUpdated.setRowETag(instance.getRowETag());
                        instanceToBeUpdated.setProperties(instance.getProperties());
                        // instanceToBeUpdated.setXmlContent(xmlParserUtil.updateInstanceXmlbyKeyValueMapAsString(instanceToBeUpdated,
                        // form, instance.getProperties(), instanceToBeUpdated.getId(),
                        // userMadeChanges,false, true));

                        instancesToBeUpdated.add(instanceToBeUpdated);
                    }
                } else {
                    // INSERT
                    String xmlContent = xmlParserUtil.createXMLDocAsString(form.getXmlFormId(),
                            instance.getProperties());
                    instance.setEnvSlug(form.getEnvSlug());
                    instance.setXmlContent(xmlContent);
                    // TODO set all properties of instance
                    // instanceService.create(instance);
                    instancesToBeInserted.add(instance);
                }
            }

            if (instancesToBeInserted.size() > 0) {
                instanceService.create_batch(instancesToBeInserted);
            }

            for (Instance instanceUpdate : instancesToBeUpdated) {
                instanceUpdate.setXmlContent(xmlParserUtil.updateInstanceXmlbyKeyValueMapAsString(instanceUpdate, form,
                        instanceUpdate.getProperties(), instanceUpdate.getId(), userMadeChanges, false, true, false));
                instanceService.update(instanceUpdate.getId(), instanceUpdate, instanceUpdate.getEnvSlug());
            }

            messageStr += "Total Instances to be Inserted in ASIMS: " + instancesToBeInserted.size() + "<br>";
            messageStr += "Total Instances to be Updated in ASIMS: " + instancesToBeUpdated.size() + "<br>";
            messageStr += "After Sync the Total Instances in ASIMS: " + instanceService.getCountByFormId(form.getId())
                    + "<br>";
        } else {
            throw new InvalidObjectException("Invalid object: " + rowsStr);
        }

        return messageStr;
    }

    @Override
    public String syncFormAttachments(Form form, User loggedInUser, String syncType, String rowsStr)
            throws ParserConfigurationException, IOException {
        // System.out.println(rowsStr);
        String messageStr = "";
        form = formService.findById(form.getId());

        if (jsonParserUtil.isValid(rowsStr)) {
            JSONObject rowsObj = jsonParserUtil.parse(rowsStr);

            JSONArray rowsArray = rowsObj.getJSONArray("rows");
            boolean hasAttachmentField = false;
            messageStr += "Total ODK Sync endpoint Records: " + rowsArray.length() + "<br>";
            for (int rowIndex = 0; rowIndex < rowsArray.length(); rowIndex++) {
                JSONObject row = rowsArray.getJSONObject(rowIndex);
                String rowId = row.getString("id");
                JSONArray orderedColumns = row.getJSONArray("orderedColumns");

                System.out.println(orderedColumns.toString());

                // with this condition it will be executed only on first loop. Ignored by rest
                // loop if form has attachment
                if (!hasAttachmentField) {
                    // only loop first entry and check it the form has attachments or not
                    // attachment column has substring of '_uriFragment'
                    for (int colIndex = 0; colIndex < orderedColumns.length(); colIndex++) {
                        JSONObject columnObj = orderedColumns.getJSONObject(colIndex);
                        String column = columnObj.getString("column");
                        if (column.contains("_uriFragment")) {
                            hasAttachmentField = true;
                        }
                    }
                }

                if (!hasAttachmentField) {
                    messageStr += "<b>The form does not have file/attachment field.</b> <br>";
                    return messageStr;
                }

                String rowAttachmentsUrl = odkxURL + "tables/" + form.getXmlFormId() + "/ref/" + form.getSchemaETag()
                        + "/attachments/" + rowId + "/manifest";
                System.out.println(rowAttachmentsUrl);
                String command = curlDefault + " " + rowAttachmentsUrl + "?fetchLimit=0";

                // messageStr += "Sync Operation for " + form.getName() + " (" +
                // form.getDescription() + ") <br>";
                // messageStr += "From ID: " + form.getId() + "<br>";
                InputStream ip = executeCommand(command);
                String rowAttachmentsResult = IOUtils.toString(ip);
                List<File> instanceFiles = new ArrayList<>();
                if (jsonParserUtil.isValid(rowAttachmentsResult)) {
                    JSONObject rowAttachmentsObj = jsonParserUtil.parse(rowAttachmentsResult);
                    JSONArray rowAttachmentsArray = rowAttachmentsObj.getJSONArray("files");
                    messageStr += rowId + " has total " + rowAttachmentsArray.length() + " attachments<br>";
                    int fileCreatedCount = 0;
                    List<String> existingFileNames = new ArrayList<>();
                    for (int rowAttIndex = 0; rowAttIndex < rowAttachmentsArray.length(); rowAttIndex++) {
                        JSONObject attachmentObj = rowAttachmentsArray.getJSONObject(rowAttIndex);
                        String filename = attachmentObj.getString("filename");
                        // Integer contentLength = attachmentObj.getInt("contentLength");
                        // String contentType = attachmentObj.getString("contentType");
                        String downloadUrl = attachmentObj.getString("downloadUrl");

                        List<Instance> targetInstances = instanceService.findByFormIdAndRowId(form.getId(), rowId);
                        // It should return only one, if it is more than one, it mean rowId is not
                        // unique
                        if (targetInstances.size() > 1) {
                            throw new ConstraintViolationException("Duplicate RowId exists in database.", null, null);
                        }

                        Instance targetInstance = targetInstances.get(0);
                        String instanceFolderPath = instanceService.getInstanceFolderPath(targetInstance.getId());
                        instanceFiles = formService.getFiles(new File(instanceFolderPath), null, false);
                        for (File f : instanceFiles) {
                            existingFileNames.add(f.getName());
                        }

                        // check if the files exists or not
                        if (existingFileNames.contains(filename)) {
                            existingFileNames.remove(filename);
                        } else {
                            String attCommand = curlDefault + " " + downloadUrl;
                            InputStream attIp = executeCommand(attCommand);
                            File newFile = new File(instanceFolderPath + "/" + filename);
                            FileUtils.copyInputStreamToFile(attIp, newFile);
                            fileCreatedCount++;
                        }
                    }

                    if (fileCreatedCount > 0) {
                        messageStr += "Total " + fileCreatedCount + " files of instance are imported. <br>";
                    }

                    // delete all instance files that does not exist in odksync endpoint
                    if (existingFileNames != null && existingFileNames.size() > 0) {
                        for (File iFile : instanceFiles) {
                            if (existingFileNames.contains(iFile.getName())) {
                                iFile.delete();
                            }
                        }
                        messageStr += "Total " + existingFileNames.size() + " files of instance are deleted<br>";
                    }
                }
            }
        } else {
            throw new InvalidObjectException("Invalid object: " + rowsStr);
        }

        return messageStr;
    }

    @Override
    public String dbSyncOdkx(Form form, User loggedInUser, String rowsStr)
            throws ParserConfigurationException, InvalidObjectException, IOException {
        // System.out.println(rowsStr);
        String messageStr = "";
        form = formService.findById(form.getId());

        if (jsonParserUtil.isValidArray(rowsStr)) {
            JSONArray rowsArray = jsonParserUtil.parseAsArray(rowsStr);

            List<Instance> instances = new ArrayList<>();
            List<String> instancesRowIds = new ArrayList<>();

            String rootNodeXPath = "/" + form.getXmlFormId() + "/";

            messageStr += "Total ODK Sync endpoint Records: " + rowsArray.length() + "<br>";
            for (int rowIndex = 0; rowIndex < rowsArray.length(); rowIndex++) {
                JSONArray row = rowsArray.getJSONArray(rowIndex);

                Instance newInstance = new Instance();
                newInstance.setOwner(loggedInUser);
                newInstance.setActive(true);
                newInstance.setForm(form);
                newInstance.setEnvSlug(form.getEnvSlug());

                for (int colIndex = 0; colIndex < row.length(); colIndex++) {
                    JSONObject fieldObj = row.getJSONObject(colIndex);
                    String column = fieldObj.getString("column");
                    Object valueObj = fieldObj.get("value");
                    String value = "";

                    if (column.equals("_URI")) {
                        newInstance.setRowId(valueObj.toString());
                    } else if (column.equals("_ROW_ETAG")) {
                        newInstance.setRowETag(valueObj.toString());
                    } else if (column.equals("_CREATE_USER")) {
                        newInstance.setTitle(valueObj.toString());
                    } else if (column.equals("_SAVEPOINT_TYPE")) {
                        newInstance.setCurrentStep(valueObj.toString());
                    }

                    if (valueObj != null) {
                        value = valueObj.toString();
                    }
                    newInstance.setProperty(rootNodeXPath + column, value);

                    // System.out.println("col: " + column + " val: " + value);
                    newInstance.setProperty(rootNodeXPath + column, value);
                }

                // System.out.println("rowId: " + id);
                instancesRowIds.add(newInstance.getRowId());
                instances.add(newInstance);
            }

            // These instance should be updated
            List<Instance> instancesExistInDB = instanceService.getInstancesExistFromList(form.getId(),
                    instancesRowIds);
            Map<String, String> instanceIdsExistInDB = new HashMap<>();
            for (Instance i : instancesExistInDB) {
                instanceIdsExistInDB.put(i.getRowId(), i.getRowETag());
            }
            Set<String> instanceIdsExistInDB_rowId = instanceIdsExistInDB.keySet();

            User userMadeChanges = loggedInUser;
            List<Instance> instancesToBeInserted = new ArrayList<>();
            List<Instance> instancesToBeUpdated = new ArrayList<>();
            for (Instance instance : instances) {
                String rowId = instance.getRowId();

                if (instanceIdsExistInDB_rowId.contains(rowId)) {
                    // UPDATE
                    String rowETag = instanceIdsExistInDB.get(rowId);

                    // if _rowETag is same as instance rowETag then it means the instance is not
                    // updated
                    if (!rowETag.equals(instance.getRowETag())) {
                        List<Instance> instanceToBeUpdatedList = instanceService.findByFormIdAndRowId(form.getId(),
                                instance.getRowId());
                        // It should return only one, if it is more than one, it mean rowId is not
                        // unique
                        if (instanceToBeUpdatedList.size() > 1) {
                            throw new ConstraintViolationException("Duplicate RowId exists in database.", null, null);
                        }

                        Instance instanceToBeUpdated = instanceToBeUpdatedList.get(0);

                        instanceToBeUpdated.setRowETag(instance.getRowETag());
                        instanceToBeUpdated.setProperties(instance.getProperties());
                        // instanceToBeUpdated.setXmlContent(xmlParserUtil.updateInstanceXmlbyKeyValueMapAsString(instanceToBeUpdated,
                        // form, instance.getProperties(), instanceToBeUpdated.getId(),
                        // userMadeChanges,false, true));

                        instancesToBeUpdated.add(instanceToBeUpdated);
                    }
                } else {
                    // INSERT
                    String xmlContent = xmlParserUtil.createXMLDocAsString(form.getXmlFormId(),
                            instance.getProperties());
                    instance.setEnvSlug(form.getEnvSlug());
                    instance.setXmlContent(xmlContent);
                    // TODO set all properties of instance
                    // instanceService.create(instance);
                    instancesToBeInserted.add(instance);
                }
            }

            if (instancesToBeInserted.size() > 0) {
                instanceService.create_batch(instancesToBeInserted);
            }

            for (Instance instanceUpdate : instancesToBeUpdated) {
                instanceUpdate.setXmlContent(xmlParserUtil.updateInstanceXmlbyKeyValueMapAsString(instanceUpdate, form,
                        instanceUpdate.getProperties(), instanceUpdate.getId(), userMadeChanges, false, true, false));
                instanceService.update(instanceUpdate.getId(), instanceUpdate, instanceUpdate.getEnvSlug());
            }

            messageStr += "Total Instances to be Inserted in ASIMS: " + instancesToBeInserted.size() + "<br>";
            messageStr += "Total Instances to be Updated in ASIMS: " + instancesToBeUpdated.size() + "<br>";
            messageStr += "After Sync the Total Instances in ASIMS: " + instanceService.getCountByFormId(form.getId())
                    + "<br>";
        } else {
            throw new InvalidObjectException("Invalid object: " + rowsStr);
        }

        return messageStr;
    }

}
