package af.gov.anar.dck.common.auth;

import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

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
public class InstanceAuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InstanceService instanceService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('INSTANCE_CREATE')")
    public Instance create(Instance instance) {
        logger.info("Entry InstanceAuthService>create() - POST");
        return instanceService.create(instance);
    }

    @PreAuthorize("hasAuthority('INSTANCE_CREATE')")
    public Instance create(Long formId) {
        logger.info("Entry InstanceAuthService>create() - POST");
        return create(formId, userService.getLoggedInUser());
    }

    @PreAuthorize("hasAuthority('INSTANCE_CREATE')")
    public Instance create(Long formId, User user) {
        logger.info("Entry InstanceAuthService>create() - POST");
        return instanceService.create(formId, user);
    }

    @PreAuthorize("hasAuthority('INSTANCE_CLONE')")
    public Instance cloneInstance(Long instanceId, User loggedInUser) {
        logger.info("Entry InstanceAuthService>create() - POST");
        return instanceService.cloneInstance(instanceId, loggedInUser);
    }

    @PreAuthorize("hasAuthority('INSTANCE_DELETE')")
    public boolean delete(Long id) {
        logger.info("Entry InstanceAuthService>delete() - DELETE");
        return instanceService.delete(id);
    }

    @PreAuthorize("hasAuthority('INSTANCE_LIST')")
    public List findAllByForm(Long formId) {
        logger.info("Entry InstanceAuthService>findAllByForm() - GET");
        return instanceService.findAllByForm(formId);
    }

    @PreAuthorize("hasAuthority('INSTANCE_LIST')")
    public List findAllByFormWithoutXMLContent(Long formId) {
        logger.info("Entry InstanceAuthService>findAllByFormWithoutXMLContent() - GET");
        return instanceService.findAllByFormWithoutXMLContent(formId);
    }

    @PreAuthorize("hasAuthority('INSTANCE_LIST')")
    public Object[] findAllByFormWithoutXMLContent(DataTablesInput input, Long formId) {
        logger.info("Entry InstanceAuthService>findAllByFormWithoutXMLContent() - GET");
        return instanceService.findAllByFormWithoutXMLContent(input, formId);
    }

    @PreAuthorize("hasAuthority('INSTANCE_VIEW')")
    public Instance findById(Long id) {
        logger.info("Entry InstanceAuthService>findAll() - GET");
        return instanceService.findById(id);
    }

    @PreAuthorize("hasAuthority('INSTANCE_EDIT')")
    public boolean update(Long id, String payload, boolean notifyWatchers) {
        logger.info("Entry InstanceAuthService>update() - PUT");
        return instanceService.update(id, payload, notifyWatchers);
    }

    @PreAuthorize("hasAuthority('INSTANCE_PRINT_REPORT')")
    public File generatePdfJasperReport(Long instanceId, Long reportId) throws IOException, JRException {
        return instanceService.generatePdfJasperReport(instanceId, reportId);
    }

    @PreAuthorize("hasAuthority('INSTANCE_LIST')")
    public List getChildInstancesWithoutXMLContent(Long parentInstanceId) {
        return instanceService.getChildInstancesWithoutXMLContent(parentInstanceId);
    }

    @PreAuthorize("hasAuthority('INSTANCE_LIST')")
    public Object[] getChildInstancesWithoutXMLContent(DataTablesInput input, Long parentInstanceId) {
        return instanceService.getChildInstancesWithoutXMLContent(input, parentInstanceId);
    }
}
