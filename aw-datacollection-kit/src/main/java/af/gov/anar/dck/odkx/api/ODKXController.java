package af.gov.anar.dck.odkx.api;

import af.gov.anar.dck.infrastructure.exception.odkx.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import af.gov.anar.dck.common.auth.FormAuthService;
import af.gov.anar.dck.common.auth.OdkxDataAuthService;
import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.infrastructure.util.DateTimeUtil;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import af.gov.anar.dck.odkx.data.ClientVersionList;
import af.gov.anar.dck.odkx.data.OdkTablesFileManifest;
import af.gov.anar.dck.odkx.data.OdkTablesFileManifestEntry;
import af.gov.anar.dck.odkx.data.PrivilegesInfo;
import af.gov.anar.dck.odkx.data.RowList;
import af.gov.anar.dck.odkx.data.UserInfo;
import af.gov.anar.dck.odkx.data.UserInfoList;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.CustomDigestUserService;
import af.gov.anar.dck.useradministration.service.UserService;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/odkx/odktables")
public class ODKXController {

    Logger logger = LoggerFactory.getLogger(ODKXController.class);
    String preferencesAppId = "default";

    @Value("${spring.mail.to}")
    private String emailTo;

    @Value("${app.form.instance.dir}")
    private String formInstanceDir;

    @Value("${app.opendatakit.default.config}")
    private String appLevelFiles;

    @Autowired
    private CustomDigestUserService customDigestUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private FormAuthService formAuthService;

    @Autowired
    private OdkxDataAuthService odkxDataAuthService;
    
    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    ObjectMapper mapper = new ObjectMapper();

    private List<String> dbSyncFormIds = new ArrayList<>();

    /*
     * Verify appId support
     * Where the response is a list of supported appId values.
     */

    @RequestMapping(method = RequestMethod.GET, produces = { "application/json", "text/xml;charset=UTF-8",
            "application/xml;charset=UTF-8" })
    public ResponseEntity /* AppNameList */ getAppNames(HttpServletResponse response) {
        ArrayList<String> appNames = new ArrayList<String>();
        appNames.addAll(Collections.singletonList("default"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(appNames);
    }

    /*
     * Authenticate user
     */

    @RequestMapping(value = "/{appId}/privilegesInfo", method = RequestMethod.GET, produces = { "application/json",
            "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* PrivilegesInfo */ getPrivilegesInfo(@PathVariable("appId") String appId,
            HttpServletRequest request, HttpServletResponse response) throws AppNameMismatchException {

        String preferencesAppId = "default";
        if (!preferencesAppId.equals(appId)) {
            throw new AppNameMismatchException("AppName (" + appId + ") differs");
        }

        try {
            User user = userService.getLoggedInUser();
            UserDetails userDetails = customDigestUserService.loadUserByUsername(user.getUsername());
            PrivilegesInfo privilegesInfo = new PrivilegesInfo(user.getId().toString(), user.getName(), null, null);
            ArrayList rules = new ArrayList<>();
            rules.add("GROUP_DATA_COLLECTORS");
            rules.add("GROUP_DATA_VIEWERS");
            rules.add("GROUP_FORM_MANAGERS");
            rules.add("ROLE_USER");
            rules.add("USER_IS_REGISTERED");
            privilegesInfo.setRoles(rules);

            // if the request includes an installation header,
            // log that the user that has been verified on that installation.
            String installationId = request.getHeader("X-OpenDataKit-Installation-Id");
            try {
                if (installationId != null) {
                    // DbInstallationInteractionLog.recordVerificationEntry(installationId, cc);
                }
            } catch (Exception e) {
                logger.warn(
                        "Unable to write verification log entry for " + installationId + " user " + user.getUsername(),
                        e);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("X-OpenDataKit-Version", "2.0");
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Credentials", "true");

            return ResponseEntity.ok().headers(headers).body(privilegesInfo);

        } catch (Exception e) {
            logger.error("Exception retrieving user privileges");
            e.printStackTrace();

            HttpHeaders headers = new HttpHeaders();
            headers.add("X-OpenDataKit-Version", "2.0");
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Credentials", "true");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers)
                    .body("Exception retrieving user privileges.");
        }
    }

    /*
     * Obtain Users List
     * This list may or may not be pruned based upon the privileges of the requesting user.
     */

    @RequestMapping(value = "/{appId}/usersInfo", method = RequestMethod.GET, produces = { "application/json",
            "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* UserInfoList */ getUsersInfo(@PathVariable("appId") String appId,
            HttpServletRequest request, HttpServletResponse response) throws AppNameMismatchException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");

        String preferencesAppId = "default";
        if (!preferencesAppId.equals(appId)) {
            throw new AppNameMismatchException("AppName (" + appId + ") differs");
        }

        User user = userService.getLoggedInUser();
        UserDetails userDetails = customDigestUserService.loadUserByUsername(user.getUsername());

        PrivilegesInfo currentUserPrivileges = new PrivilegesInfo(user.getId().toString(), user.getName(), null, null);
        ArrayList rules = new ArrayList<>();
        rules.add("GROUP_DATA_COLLECTORS");
        rules.add("GROUP_DATA_VIEWERS");
        rules.add("GROUP_FORM_MANAGERS");
        rules.add("ROLE_USER");
        rules.add("USER_IS_REGISTERED");
        currentUserPrivileges.setRoles(rules);
        boolean returnFullList = false;
        for (String grant : currentUserPrivileges.getRoles()) {
            if (grant.equals("Site Administrator") || grant.equals("Administer Tables")
                    || grant.equals("Tables Super-user")) {
                returnFullList = true;
                break;
            }
        }

        // returned object (will be JSON serialized).
        ArrayList<UserInfo> listOfUsers = new ArrayList<UserInfo>();

        if (!returnFullList) {
            // only return ourself -- we don't have privileges to see everyone
            UserInfo userInfo = new UserInfo(currentUserPrivileges.getUser_id(), currentUserPrivileges.getFull_name(),
                    currentUserPrivileges.getRoles());
            listOfUsers.add(userInfo);
        } else {
            // we have privileges to see all users -- return the full mapping
            ArrayList<UserInfo> allUsers;
            // try {
            // // allUsers = SecurityServiceUtil.getAllUsers(true, cc);
            // } catch (AccessDeniedException e) {
            // logger.error("AccessDeniedException retrieving user list");
            // e.printStackTrace();

            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            // .headers(headers)
            // .body("AccessDeniedException retrieving user list.");

            // } catch (DatastoreFailureException e) {
            // logger.error("DatastoreFailureException retrieving user list");
            // e.printStackTrace();

            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            // .headers(headers)
            // .body("DatastoreFailureException retrieving user list.");
            // }

            // for (UserSecurityInfo i : allUsers ) {
            // UserInfo userInfo = SecurityServiceUtil.createUserInfo(i);
            // listOfUsers.add(userInfo);
            // }
        }

        UserInfoList userInfoList = new UserInfoList(listOfUsers);

        return ResponseEntity.ok().headers(headers).body(userInfoList);
    };

    /*
     * Data Grouping #1 REST Synchronization API, Obtain Supported odkClientVersion
     * This returns a list of the odkClientVersion values supported by this server
     */

    @RequestMapping(value = "{appId}/clientVersions", method = RequestMethod.GET, produces = { "application/json",
            "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* ClientVersionList */ getOdkClientVersions(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest request, HttpServletResponse response, @PathVariable("appId") String appId)
            throws AppNameMismatchException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");

        String preferencesAppId = "default";

        if (!preferencesAppId.equals(appId)) {
            throw new AppNameMismatchException("AppName (" + appId + ") differs");
        }

        List<String> distinctOdkClientVersions = null;
        String eTagOdkClientVersions = null;

        // retrieve the incoming if-none-match eTag...
        List<String> eTags = httpHeaders.get(HttpHeaders.IF_NONE_MATCH);
        String eTag = (eTags == null || eTags.isEmpty()) ? null : eTags.get(0);
        // try {
        // distinctOdkClientVersions = DbTableFileInfo.queryForAllOdkClientVersions(cc);
        distinctOdkClientVersions = Collections.singletonList("2");
        eTagOdkClientVersions = Integer
                .toHexString((distinctOdkClientVersions == null) ? -1 : distinctOdkClientVersions.hashCode());

        if (eTag != null && distinctOdkClientVersions != null && eTag.equals(eTagOdkClientVersions)) {
            headers.add(HttpHeaders.ETAG, eTag);
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).headers(headers).body("");
        }
        // } catch (ODKDatastoreException e) {
        // logger.error("Datastore exception in getting the file manifest");
        // e.printStackTrace();
        // }
        if (distinctOdkClientVersions == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers)
                    .body("Unable to retrieve odkClientVersions.");
        } else {
            // UriBuilder ub = info.getBaseUriBuilder();
            // ub.path(OdkTables.class, "getOdkClientVersions");

            ClientVersionList clientVersions = new ClientVersionList(distinctOdkClientVersions);
            headers.add(HttpHeaders.ETAG, eTagOdkClientVersions);
            return ResponseEntity.ok().headers(headers).body(clientVersions);
        }
    }

    /*
     * Data Grouping #1 REST Synchronization API, Manifest REST Api Requests
     * the manifest of all app-level files for an appId and odkClientVersion
     */

    @RequestMapping(value = "{appId}/manifest/{odkClientVersion}", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* OdkTablesFileManifest */ getAppLevelFileManifest(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("odkClientVersion") String odkClientVersion) throws IOException, AppNameMismatchException {

        if (!preferencesAppId.equals(appId)) {
            throw new AppNameMismatchException("AppName (" + appId + ") differs");
        }
        String fullURL = req.getRequestURL().toString();
        String baseURL = fullURL.substring(0, StringUtils.ordinalIndexOf(fullURL, "/", 5));
        List<File> filesInFolder = Files.walk(Paths.get(appLevelFiles)).filter(Files::isRegularFile).map(Path::toFile)
                .collect(Collectors.toList());
        ServletContext sc = req.getServletContext();
        ArrayList<OdkTablesFileManifestEntry> resources = new ArrayList<OdkTablesFileManifestEntry>();
        long length;
        String fileName;
        Path path;
        String mimeType;
        for (File element : filesInFolder) {
            mimeType = sc.getMimeType(element.getAbsolutePath());
            path = element.toPath();
            fileName = path.subpath(3, path.getNameCount()).toString().replace("\\", "/");
            length = element.length();
            resources.add(new OdkTablesFileManifestEntry(fileName, length, mimeType, "md5hash",
                    baseURL + "/" + appId + "/files/" + odkClientVersion + "/" + fileName.replace("\\", "/")));
        }
        OdkTablesFileManifest files = new OdkTablesFileManifest(resources);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(files);
    }

    /*
     * Data Grouping #1 REST Synchronization API, Download App-Level File REST API
     */

    @RequestMapping(value = "{appId}/files/{odkClientVersion}/**", method = RequestMethod.GET, produces = { "*" })
    public ResponseEntity getFile(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("odkClientVersion") String odkClientVersion,
            @RequestParam(value = "as_attachment", required = false) String asAttachment)
            throws IOException, AppNameMismatchException, FileNotFoundException {
        if (!preferencesAppId.equals(appId)) {
            throw new AppNameMismatchException("AppName (" + appId + ") differs");
        }
        ServletContext sc = req.getServletContext();
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher apm = new AntPathMatcher();
        String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
        File file = new File(appLevelFiles + "/" + finalPath);
        if (file.exists()) {
            String contentType = sc.getMimeType(file.getAbsolutePath());
            response.setContentType(contentType);
            response.setContentLength((int) file.length());
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            // copy from in to out
            IOUtils.copy(in, out);
            out.close();
            in.close();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-OpenDataKit-Version", "2.0");
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Credentials", "true");
            return ResponseEntity.ok().headers(headers).body(out);
        } else {
            throw new FileNotFoundException("No manifest entry found for: " + finalPath);
        }

    }

    /*
     * Data Grouping #1 REST Synchronization API, Upload App-Level File REST API
     */

    @RequestMapping(value = "{appId}/files/{odkClientVersion}/**", method = RequestMethod.POST, consumes = { "*" })
    public ResponseEntity putFile(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("odkClientVersion") String odkClientVersion,
            @RequestParam(value = "content", required = true) MultipartFile file)
            throws IOException, ODKTaskLockException, PermissionDeniedException, ODKDatastoreException {
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        Path p = Paths.get(path);
        String finalPath = p.subpath(5, p.getNameCount()-1).toString().replace("\\", "/");
        String uploadFileLocation = appLevelFiles + "/" + finalPath;

        if (!file.isEmpty()) {
			try {
				File newFile = new File(uploadFileLocation);
				if (!newFile.exists()) {
					newFile.mkdirs();
				}
				String completeFilePathName = uploadFileLocation +"/"+ file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				Path uploadPath = Paths.get(completeFilePathName);
				Files.write(uploadPath, bytes);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(uploadFileLocation);
    }

    /*
     * Data Grouping #1 REST Synchronization API, Delete App-Level File REST API
     */

    @RequestMapping(value = "{appId}/files/{odkClientVersion}/**", method = RequestMethod.DELETE)
    public ResponseEntity deleteFile(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("odkClientVersion") String odkClientVersion)
            throws IOException, FileNotFoundException, AppNameMismatchException {
        if (!preferencesAppId.equals(appId)) {
            throw new AppNameMismatchException("AppName (" + appId + ") differs");
        }
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher apm = new AntPathMatcher();
        String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
        File file = new File(appLevelFiles + "/" + finalPath);
        if (file.delete()) {

        } else {
            throw new FileNotFoundException("No manifest entry found for: " + finalPath);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body("");
    }

    /*
     * Data Grouping #2 REST Synchronization, List All Table Resources API
     */

    @RequestMapping(value = "{appId}/tables", method = RequestMethod.GET, produces = { "application/json",
            "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* TableResourceList */ getTables(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @RequestParam(value = "cursor", required = false) String cursor,
            @RequestParam(value = "fetchLimit", required = false) String fetchLimit)
            throws ODKDatastoreException, AppNameMismatchException, PermissionDeniedException, ODKTaskLockException {
        ServletContext sc = req.getServletContext();
        // HttpSession session = req.getSession();
        // ServletContext sc = session.getServletContext();
        // ServletContext sc = req.getSession().getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body("");

    }

    /*
     * Data Grouping #2 REST Synchronization, Get Table Resource API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}", method = RequestMethod.GET, produces = { "application/json",
            "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity getTablesService(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId)
            throws ODKDatastoreException, AppNameMismatchException, PermissionDeniedException, ODKTaskLockException,
            TableNotFoundException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(tableId);
    }

    /*
     * Data Grouping #2 REST Synchronization, Create Table Resource API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}", method = RequestMethod.PUT, produces = { "application/json",
            "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" }, consumes = { "application/json",
                    "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* TableResource */ createTable(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("tableId") String tableId, @RequestParam MultiValueMap<String, String> definition)
            throws ODKDatastoreException, AppNameMismatchException, TableAlreadyExistsException,
            PermissionDeniedException, ODKTaskLockException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(tableId);
    }

    /*
     * Data Grouping #2 REST Synchronization, Get Table Definition API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* TableDefinitionResource */ getDefinition(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("tableId") String tableId, @PathVariable("schemaETag") String schemaETag)
            throws ODKDatastoreException, AppNameMismatchException, PermissionDeniedException, ODKTaskLockException,
            TableNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #2 REST Synchronization, Delete Table Definition API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}", method = RequestMethod.DELETE)
    public ResponseEntity /* void */ deleteTable(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag)
            throws ODKDatastoreException, AppNameMismatchException, ODKTaskLockException, PermissionDeniedException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);

    }

    /*
     * Data Grouping #2 REST Synchronization -- Table-level Files API the manifest
     * of all table-level files for an appId and odkClientVersion and tableId
     */

    @RequestMapping(value = "{appId}/manifest/{odkClientVersion}/{tableId}", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* OdkTablesFileManifest */ getTableIdFileManifest(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("odkClientVersion") String odkClientVersion, @PathVariable("tableId") String tableId)
            throws ODKEntityNotFoundException, ODKOverQuotaException, PermissionDeniedException, ODKDatastoreException,
            ODKTaskLockException {

        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(tableId);
    }

    /*
     * Data Grouping #3 REST Synchronization Get All Data Changes Since... API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/diff", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* RowResourceList */ getRowsSince(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("tableId") String tableId, @PathVariable("schemaETag") String schemaETag,
            @RequestParam(value = "data_etag", required = false) String dataETag,
            @RequestParam(value = "cursor", required = false) String cursor,
            @RequestParam(value = "fetchLimit", required = false) String fetchLimit) throws ODKDatastoreException,
            PermissionDeniedException, InconsistentStateException, ODKTaskLockException, BadColumnNameException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Get Changesets API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/diff/changeSets", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* ChangeSetList */ getChangeSetsSince(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("tableId") String tableId, @PathVariable("schemaETag") String schemaETag,
            @RequestParam(value = "data_etag", required = false) String dataETag,
            @RequestParam(value = "sequence_value", required = false) String sequenceValue)
            throws ODKDatastoreException, PermissionDeniedException, InconsistentStateException, ODKTaskLockException,
            BadColumnNameException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Get Changeset Rows API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/diff/changeSets/{dataETag}", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* RowResourceList */ getChangeSetRows(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("tableId") String tableId, @PathVariable("schemaETag") String schemaETag,
            @PathVariable("dataETag") String dataETag,
            @RequestParam(value = "active_only", required = false) String isActive,
            @RequestParam(value = "cursor", required = false) String cursor,
            @RequestParam(value = "fetchLimit", required = false) String fetchLimit) throws ODKDatastoreException,
            PermissionDeniedException, InconsistentStateException, ODKTaskLockException, BadColumnNameException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Get All Data Rows API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/rows", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* RowResourceList */ getRows(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag,
            @RequestParam(value = "cursor", required = false) String cursor,
            @RequestParam(value = "fetchLimit", required = false) String fetchLimit) throws ODKDatastoreException,
            PermissionDeniedException, InconsistentStateException, ODKTaskLockException, BadColumnNameException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Get a Data Row API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/rows/{rowId}", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* RowResource */ getRow(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag, @PathVariable("rowId") String rowId)
            throws ODKDatastoreException, PermissionDeniedException, InconsistentStateException, ODKTaskLockException,
            BadColumnNameException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(rowId);
    }

    /*
     * Data Grouping #3 REST Synchronization Alter Data Rows (Insert, Update or
     * Delete)API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/rows", method = RequestMethod.PUT, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" }, consumes = {
                    "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* RowOutcomeList */ alterRows(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag, RowList rows)
            throws ODKTaskLockException, ODKDatastoreException, PermissionDeniedException, BadColumnNameException,
            InconsistentStateException, TableDataETagMismatchException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Get Manifest of Attachments API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/attachments/{rowId}/manifest", method = RequestMethod.GET, produces = {
            "application/json", "text/xml;charset=UTF-8", "application/xml;charset=UTF-8" })
    public ResponseEntity /* OdkTablesFileManifest */ getManifest(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("tableId") String tableId, @PathVariable("schemaETag") String schemaETag,
            @PathVariable("rowId") String rowId,
            @RequestParam(value = "as_attachment", required = false) String asAttachment)
            throws IOException, ODKTaskLockException, PermissionDeniedException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Multipart Get Attachment API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/attachments/{rowId}/download", method = RequestMethod.POST, consumes = {
            "application/json", "text/xml;charset=UTF-8",
            "application/xml;charset=UTF-8" }, produces = { "multipart/form-data" })
    public ResponseEntity getFiles(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag, @PathVariable("rowId") String rowId,
            OdkTablesFileManifest manifest) throws IOException, ODKTaskLockException, PermissionDeniedException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Multipart Put Attachment API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/attachments/{rowId}/upload", method = RequestMethod.POST, produces = {
            "application/json", "text/xml;charset=UTF-8",
            "application/xml;charset=UTF-8" }, consumes = { "multipart/form-data" })
    public ResponseEntity postFiles(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag, @PathVariable("rowId") String rowId,
            OdkTablesFileManifest manifest, MultipartFile inMP)
            throws IOException, ODKTaskLockException, ODKTablesException, ODKDatastoreException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Data Grouping #3 REST Synchronization Get Attachment API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/attachments/{rowId}/file/**", method = RequestMethod.GET, produces = {
            "*" })
    public ResponseEntity getFile(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag, @PathVariable("rowId") String rowId,
            @RequestParam(value = "as_attachment", required = false) String asAttachment)
            throws IOException, ODKTaskLockException, PermissionDeniedException {
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher apm = new AntPathMatcher();
        String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(finalPath);
    }

    /*
     * Data Grouping #3 REST Synchronization Put Attachment API
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/attachments/{rowId}/file/**", method = RequestMethod.POST, consumes = {
            "*" })
    public ResponseEntity putFile(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, @PathVariable("tableId") String tableId,
            @PathVariable("schemaETag") String schemaETag, @PathVariable("rowId") String rowId, byte[] content)
            throws IOException, ODKTaskLockException, PermissionDeniedException, ODKDatastoreException {
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher apm = new AntPathMatcher();
        String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(finalPath);
    }

    /*
     * Report table status metrics
     */

    @RequestMapping(value = "{appId}/tables/{tableId}/ref/{schemaETag}/installationStatus", method = RequestMethod.POST, consumes = {
            "application/json" })
    public ResponseEntity /* OK */ postInstallationStatus(@RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest req, HttpServletResponse response, @PathVariable("appId") String appId,
            @PathVariable("tableId") String tableId, @PathVariable("schemaETag") String schemaETag, Object body)
            throws AppNameMismatchException, PermissionDeniedException, ODKDatastoreException, ODKTaskLockException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(schemaETag);
    }

    /*
     * Report device info and overall sync state
     */

    @RequestMapping(value = "{appId}/installationInfo", method = RequestMethod.POST, consumes = { "application/json" })
    public ResponseEntity /* OK */ postInstallationInfo(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, Object body)
            throws AppNameMismatchException, PermissionDeniedException, ODKDatastoreException, ODKTaskLockException {
        ServletContext sc = req.getServletContext();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-OpenDataKit-Version", "2.0");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Credentials", "true");
        return ResponseEntity.ok().headers(headers).body(appId);
    }


    /**
     * Below methods are for getting data from odkx.nsia.gov.af through RESTful APIs
     */
    @RequestMapping(value = "{appId}/tables/privilegesInfo", method = RequestMethod.GET, consumes = { "application/json" })
    public String /* OK */ tableList(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest req,
            HttpServletResponse response, @PathVariable("appId") String appId, Object body)
            throws AppNameMismatchException, PermissionDeniedException, ODKDatastoreException, ODKTaskLockException, IOException {
        // ServletContext sc = req.getServletContext();
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("X-OpenDataKit-Version", "2.0");
        // headers.add("Access-Control-Allow-Origin", "*");
        // headers.add("Access-Control-Allow-Credentials", "true");

        System.out.println("INSIDE the ODKX controller-----------------3333");

        String command = "curl -i -H 'Accept:application/json'  -H 'Authorization: Basic aHBhcmRlc3M6S2FidWxAMTIz'   http://odkx.nsia.gov.af/odktables/default/privilegesInfo";
            
        Process process = Runtime.getRuntime().exec(command);
        InputStream ip = process.getInputStream();
        // Scanner scanner = new Scanner(ip).useDelimiter("\\A");

        // String result = scanner.hasNext() ? scanner.next() : "";
        String result = IOUtils.toString(ip);

        System.out.println("------------------------2345");
        System.out.println(result);

        return result;
    }































    // This function is called through Cron Job to sync all the data directly from database. Also attachments are synced if exists
    @RequestMapping(value = "/db_sync/{xmlFormId}", method = RequestMethod.POST)
    public boolean dbSyncOdkxFormData_all(HttpServletRequest request, 
        @PathVariable("xmlFormId") String xmlFormId, @RequestBody String rowsJson) throws IOException {
        System.out.println("Comming data:  "+ rowsJson);

        User currentLoggedInUser = userService.getLoggedInUser();
        try {
            
            Form form = formAuthService.findByXmlFormId(xmlFormId);

            if(form == null) {
                String errorStr = xmlFormId + " Form does not exist in ASIMS";
                emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - DB Level Odkx Sync", errorStr, null, currentLoggedInUser, null, request);
                return false;
            }

            if(dbSyncFormIds.contains(xmlFormId)) {
                String errorStr = form.getName() + " is aleady in progress to be synced";
                emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - DB Level Odkx Sync", errorStr, null, currentLoggedInUser, null, request);
                return false;
            }
            dbSyncFormIds.add(xmlFormId);

            String messageStr = "<br><br>DB Level Sync Operation for " + form.getName() + " (" + form.getDescription() + ") <br>";
            messageStr += "From ID: " + form.getId() + "<br>";

            messageStr += "Sync Start DateTime: " + dateTimeUtil.getCurrentDate() + "<br>";

            messageStr += odkxDataAuthService.dbSyncOdkx(form, currentLoggedInUser, rowsJson);
            
            messageStr += "Sync End DateTime: " + dateTimeUtil.getCurrentDate() + "<br>";
            emailUtil.sendMessageWithDetails(emailTo, "200 - ASIMS - DB Level Odkx Sync Operation", messageStr, null, currentLoggedInUser, null, request);

            dbSyncFormIds.remove(xmlFormId);
            return true;
        } catch (Exception e) {
            dbSyncFormIds.remove(xmlFormId);
            String errorStr = ExceptionUtils.getStackTrace(e);
            emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - DB Level Odkx Sync", errorStr, null, currentLoggedInUser, null, request);
            return false;
        }
    }

}
