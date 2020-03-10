package af.gov.anar.dck.common.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.odkx.service.OdkxDataService;
import af.gov.anar.dck.useradministration.model.User;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;

/**
 * @author Hameedullah Pardess
 */

@Service
public class OdkxDataAuthService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OdkxDataService odkxDataService;

    @PreAuthorize("hasAuthority('ODK_X_SYNC')")
    public String syncFormData(Form form, User loggedInUser, String syncType, String rowsJson)
            throws ParserConfigurationException, InvalidObjectException {
        logger.info("Entry OdkxDataAuthService>syncFormData() - GET");
        return odkxDataService.syncFormData(form, loggedInUser, syncType, rowsJson);
    }

    @PreAuthorize("hasAuthority('ODK_X_SYNC')")
    public String syncFormAttachments(Form form, User loggedInUser, String syncType, String rowsJson)
            throws ParserConfigurationException, IOException {
        logger.info("Entry OdkxDataAuthService>syncFormAttachments() - GET");
        return odkxDataService.syncFormAttachments(form, loggedInUser, syncType, rowsJson);
    }

    @PreAuthorize("hasAuthority('ODK_X_SYNC')")
    public String dbSyncOdkx(Form form, User loggedInUser, String rowsJson)
        throws ParserConfigurationException, IOException {

        logger.info("Entry OdkxDataAuthService>dbSyncOdkx() - POST");
        return odkxDataService.dbSyncOdkx(form, loggedInUser, rowsJson);
    }
    
    public InputStream executeCommand(String command) throws IOException {
        return odkxDataService.executeCommand(command);
    }
}
