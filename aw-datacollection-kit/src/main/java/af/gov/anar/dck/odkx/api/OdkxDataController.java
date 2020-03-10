package af.gov.anar.dck.odkx.api;

import af.gov.anar.dck.common.auth.FormAuthService;
import af.gov.anar.dck.common.auth.OdkxDataAuthService;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.infrastructure.util.DateTimeUtil;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import af.gov.anar.dck.useradministration.model.User;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirwais Akrami mail: m7akrami770@gmail.com Controller to fetch data
 *         from odkx sync endpoint REST APIs through CURL
 */
@RestController
@RequestMapping("/api/odkx_data/")
public class OdkxDataController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.to}")
    private String emailTo;

    @Value("${odkx.syncEndpoint.odkxURL}")
    private String odkxURL;

    @Value("${odkx.syncEndpoint.curlDefault}")
    private String curlDefault;

    @Autowired
    private FormAuthService formAuthService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private OdkxDataAuthService odkxDataAuthService;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
	private ExceptionUtil exceptionUtil;

    private String failureEmailSub = "500 - ASIMS Exception - Odkx Sync";
    
    @Autowired
    private DateTimeUtil dateTimeUtil;

    // private String username = "hpardess";
    // private String password = "Kabul@123";
    // private static final String odkxURL =
    // "http://odkx.nsia.gov.af/odktables/default/";
    // private final String curlDefault = "curl -H 'Accept:application/json' -u " +
    // username + ":" + password;

    private List<Long> syncFormIds = new ArrayList<>();

    @GetMapping(value = { "/sync/all" })
    public boolean syncOdkxAllFormData_all(HttpServletRequest request) {
        String envSlug = userAuthService.getCurrentEnv();
        List<Form> odkxForms = formAuthService.findByFormCategoryAndEnvSlug(1, envSlug);

        User currentLoggedInUser = userAuthService.getLoggedInUser();
        for (Form form : odkxForms) {
            try {

                // if (syncFormIds.contains(form.getId())) {
                //     String errorStr = form.getName() + " is aleady in progress to be synced";
                //     emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - Odkx Sync", errorStr, null,
                //             currentLoggedInUser, userAuthService.getCurrentEnv(), request);
                //     return false;
                // }

                // syncFormIds.add(form.getId());

                String messageStr = syncOdkxFormData(form, currentLoggedInUser, "data");

                emailUtil.sendMessageWithDetails(emailTo, "200 - ASIMS - Odkx Sync Operation", messageStr, null,
                        currentLoggedInUser, userAuthService.getCurrentEnv(), request);

                // syncFormIds.remove(form.getId());
                // return true;
            } catch (Exception e) {
                syncFormIds.remove(form.getId());
                String errorStr = ExceptionUtils.getStackTrace(e);
                emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - Odkx Sync", errorStr, null,
                        currentLoggedInUser, userAuthService.getCurrentEnv(), request);
                // return false;
            }
        }
        return true;
    }

    @GetMapping(value = { "/sync/{formId}/{syncType}" })
    public boolean syncOdkxFormData_all(HttpServletRequest request, @PathVariable("formId") Long formId,
            @PathVariable("syncType") String syncType) {

        if (!syncType.equals("data") && !syncType.equals("attachment")) {
            return false;
        }

        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formAuthService.findById(formId);

            // if (syncFormIds.contains(formId)) {
            //     String errorStr = form.getName() + " is aleady in progress to be synced";
            //     emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - Odkx Sync", errorStr, null,
            //             currentLoggedInUser, userAuthService.getCurrentEnv(), request);
            //     return false;
            // }

            

            String messageStr = syncOdkxFormData(form, currentLoggedInUser, syncType);
            emailUtil.sendMessageWithDetails(emailTo, "200 - ASIMS - Odkx Sync Operation", messageStr, null,
                    currentLoggedInUser, userAuthService.getCurrentEnv(), request);

            return true;
        } catch (Exception e) {
            syncFormIds.remove(formId);
            String errorStr = ExceptionUtils.getStackTrace(e);
            emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - Odkx Sync", errorStr, null,
                    currentLoggedInUser, userAuthService.getCurrentEnv(), request);
            return false;
        }
    }

    public String syncOdkxFormData(Form form, User loggedInUser, String syncType) throws IOException, ParserConfigurationException {
        if (syncFormIds.contains(form.getId())) {
            return form.getName() + " is aleady in progress to be synced";
        }

        syncFormIds.add(form.getId());

        String rowsUrl = odkxURL + "tables/" + form.getXmlFormId() + "/ref/" + form.getSchemaETag() + "/rows";
        // String colsUrl = odkxURL + "tables/" + form.getXmlFormId() + "/ref/" + form.getSchemaETag();

        System.out.println(rowsUrl);
        // System.out.println(colsUrl);
        // String command = curlDefault + " " + rowsUrl + " " + colsUrl;
        String command = curlDefault + " " + rowsUrl + "?fetchLimit=0";

        String messageStr = "<br><br>Sync Operation for " + form.getName() + " (" + form.getDescription() + ") <br>";
        messageStr += "From ID: " + form.getId() + "<br>";
        InputStream ip = odkxDataAuthService.executeCommand(command);
        String result = IOUtils.toString(ip);

        messageStr += "Sync Start DateTime: " + dateTimeUtil.getCurrentDate() + "<br>";

        if(syncType.equals("data")) {
            messageStr += odkxDataAuthService.syncFormData(form, loggedInUser, syncType, result);
        } else if(syncType.equals("attachment")) {
            messageStr += odkxDataAuthService.syncFormAttachments(form, loggedInUser, syncType, result);
        }
        
        messageStr += "Sync End DateTime: " + dateTimeUtil.getCurrentDate() + "<br>";

        syncFormIds.remove(form.getId());
        return messageStr;
    }



















    @Loggable
    @GetMapping(value = "/privileges")
    public String getUserPrivileges(HttpServletRequest request) throws IOException {

        String url = odkxURL + "privilegesInfo";

        String command = curlDefault + " " + url;

        return IOUtils.toString(odkxDataAuthService.executeCommand(command));
    }

    @Loggable
    @GetMapping(value = { "/tables" })
    public String getTables(HttpServletRequest request) throws IOException {

        String url = odkxURL + "tables";

        String command = curlDefault + " " + url;
        return IOUtils.toString(odkxDataAuthService.executeCommand(command));
    }

    @Loggable
    @GetMapping(value = { "/tables/{id}/ref/{schemaETage}" })
    public String getTableColumns(HttpServletRequest request, @PathVariable("id") String tableId,
            @PathVariable("schemaETag") String schemaETag) throws IOException {

        String url = odkxURL + "tables/" + tableId + "/" + schemaETag;

        String command = curlDefault + " " + url;
        return IOUtils.toString(odkxDataAuthService.executeCommand(command));
    }

    @Loggable
    @GetMapping(value = { "/tables/{id}/ref/{schemaETage}/rows" })
    public String getTableRows(HttpServletRequest request, @PathVariable("id") String tableId,
            @PathVariable("schemaETag") String schemaETag) throws IOException {

        String url = odkxURL + "tables/" + tableId + "/" + schemaETag + "/rows";

        String command = curlDefault + " " + url;
        return IOUtils.toString(odkxDataAuthService.executeCommand(command));
    }

    @Loggable
    @GetMapping(value = { "/tables/{id}/ref/{schemaETage}/rows/{rowId}" })
    public String getSingleRow(HttpServletRequest request, @PathVariable("id") String tableId,
            @PathVariable("rowId") String rowId, @PathVariable("schemaETag") String schemaETag) throws IOException {

        String url = odkxURL + "tables/" + tableId + "/" + schemaETag + "/rows/" + rowId;

        String command = curlDefault + " " + url;
        return IOUtils.toString(odkxDataAuthService.executeCommand(command));
    }

    @Loggable
    @GetMapping(value = { "/tables/{id}/ref/{schemaETage}/full" })
    public String getTablesData(HttpServletRequest request, @PathVariable("id") String tableId,
            @PathVariable("schemaETag") String schemaETag) throws IOException {

        String rowsUrl = odkxURL + "tables/" + tableId + "/" + schemaETag + "/rows";
        String colsUrl = odkxURL + "tables/" + tableId + "/" + schemaETag;

        String command = curlDefault + " " + rowsUrl + " " + colsUrl;
        return IOUtils.toString(odkxDataAuthService.executeCommand(command));
    }

    @Loggable
    @GetMapping(value = { "/manifest/{odkClientVersion}/{id}" })
    public String getTableLevelFiles(HttpServletRequest request, @PathVariable("id") String tableId,
            @PathVariable("odkClientVersion") String odkClientVersion) throws IOException {

        String url = odkxURL + odkClientVersion + "/" + tableId;

        String command = curlDefault + " " + url;
        return IOUtils.toString(odkxDataAuthService.executeCommand(command));
    }

}
