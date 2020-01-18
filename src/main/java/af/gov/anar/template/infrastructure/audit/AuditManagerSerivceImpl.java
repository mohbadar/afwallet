package af.gov.anar.template.infrastructure.audit;

import static af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants.APPLICATION_ID;
import static af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants.APPLICATION_NAME;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import af.gov.anar.lang.Application;
import af.gov.anar.lang.infrastructure.exception.common.ExceptionUtils;
import af.gov.anar.lib.audit.builder.AuditRequestBuilder;
import af.gov.anar.lib.audit.data.AuditRequestDto;
import af.gov.anar.lib.audit.handler.AuditHandler;
import af.gov.anar.lib.date.DateUtility;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.template.infrastructure.audit.control.entity.AuditLogControl;
import af.gov.anar.template.infrastructure.audit.control.service.AuditLogControlDAO;
import af.gov.anar.template.infrastructure.audit.control.service.BaseService;
import af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.template.infrastructure.dto.ResponseDTO;
import af.gov.anar.template.infrastructure.enumeration.AuditEvent;
import af.gov.anar.template.infrastructure.enumeration.Components;
import af.gov.anar.template.infrastructure.service.HostService;
import af.gov.anar.template.infrastructure.service.UserService;
import af.gov.anar.template.infrastructure.util.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Class to Audit the events of Registration Client.
 * <p>
 * This class creates a wrapper around {@link AuditRequestBuilder} class. This
 * class creates a {@link AuditRequestBuilder} object for each audit event and
 * persists the same using {@link AuditHandler} .
 *
 * @author Balaji Sridharan
 * @since 1.0.0
 *
 */
@Service
public class AuditManagerSerivceImpl  extends BaseService implements AuditManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditManagerSerivceImpl.class);

    @Autowired
    private AuditHandler<AuditRequestDto> auditHandler;

    @Value(ApplicationGenericConstants.AUDIT_LOG_DELETION_CONFIGURED_DAYS)
    private String auditLogDeletionConfiuredDays;


    @Autowired
    private AuditLogControlDAO auditLogControlDAO;

    @Autowired
    private HostService hostService;

    @Autowired
    private UserService userService;


    /*
     * (non-Javadoc)
     *
     * @see io.mosip.registration.audit.AuditFactory#audit(io.mosip.registration.
     * constants.AuditEvent, io.mosip.registration.constants.Components,
     * java.lang.String, java.lang.String)
     */
    public void audit(AuditEvent auditEventEnum, Components appModuleEnum, String refId, String refIdType) {

        String hostIP = hostService.SERVICE_HOST;
        String hostName = hostService.SERVICE_HOST;

        try {
        // Getting Host IP Address and Name
         hostIP = hostService.getDefaultIP();
         hostName = hostService.getDefaultHostName();

        } catch (UnknownHostException unknownHostException) {
            LOGGER.info("SERVICETE-TEMPLATE-AUDIT_FACTORY-AUDIT", APPLICATION_NAME, APPLICATION_ID,
                    ExceptionUtils.getStackTrace(unknownHostException));
        }

        AuditRequestBuilder auditRequestBuilder = new AuditRequestBuilder();
        auditRequestBuilder.setActionTimeStamp(LocalDateTime.now(ZoneOffset.UTC))
                .setApplicationId(String.valueOf(APPLICATION_ID))
                .setApplicationName(String.valueOf(APPLICATION_NAME))
                .setCreatedBy(userService.getPreferredUsername()).setDescription(auditEventEnum.getDescription())
                .setEventId(auditEventEnum.getId()).setEventName(auditEventEnum.getName())
                .setEventType(auditEventEnum.getType()).setHostIp(hostIP).setHostName(hostName).setId(refId)
                .setIdType(refIdType).setModuleId(appModuleEnum.getId()).setModuleName(appModuleEnum.getName())
                .setSessionUserId(userService.getId()).setSessionUserName(userService.getPreferredUsername());
        auditHandler.addAudit(auditRequestBuilder.build());
    }

    @Override
    public void audit(af.gov.anar.lib.audit.util.sample.AuditEvent auditEventEnum, Components appModuleEnum, String refId, String refIdType) {

    }

    /*
     * (non-Javadoc)
     *
     * @see io.mosip.registration.service.audit.AuditService#deleteAuditLogs()
     */
    @Override
    public synchronized ResponseDTO deleteAuditLogs() {

        LOGGER.info(ApplicationGenericConstants.AUDIT_SERVICE_LOGGER_TITLE, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Deletion of Audit Logs Started");

        ResponseDTO responseDTO = new ResponseDTO();

        if (auditLogDeletionConfiuredDays != null) {
            try {
                int auditDeletionConfiguredDays = Integer.parseInt(auditLogDeletionConfiuredDays);

                /* Get Calendar instance */
                Calendar cal = Calendar.getInstance();
                cal.setTime(Timestamp.valueOf(DateUtility.getUTCCurrentDateTime()));
                cal.add(Calendar.DATE, -auditDeletionConfiguredDays);

                /* To-Date */
                Timestamp req = new Timestamp(cal.getTimeInMillis());

                /* Fetch Audit Log Controls Using req Time */
                List<AuditLogControl> auditLogControls = auditLogControlDAO.get(req);

                if (isNull(auditLogControls) || isEmpty(auditLogControls)) {

                    /* No Audit Logs Found */
                    return setSuccessResponse(responseDTO, ApplicationGenericConstants.AUDIT_LOGS_DELETION_EMPTY_MSG, null);

                }

                List<String> regIds = auditLogControls.stream().map(auditControl -> {
                    return auditControl.getRegistrationId();
                }).collect(Collectors.toList());

                /* Fetch Registartions to be deleted */

                setSuccessResponse(responseDTO, ApplicationGenericConstants.AUDIT_LOGS_DELETION_SUCESS_MSG, null);

            } catch (RuntimeException runtimeException) {
                LOGGER.error(ApplicationGenericConstants.AUDIT_SERVICE_LOGGER_TITLE, ApplicationGenericConstants.APPLICATION_NAME,
                        ApplicationGenericConstants.APPLICATION_ID, runtimeException.getMessage());

                setErrorResponse(responseDTO, ApplicationGenericConstants.AUDIT_LOGS_DELETION_FLR_MSG, null);

            }

        } else {
            setErrorResponse(responseDTO, ApplicationGenericConstants.AUDIT_LOGS_DELETION_FLR_MSG, null);
        }

        LOGGER.info(ApplicationGenericConstants.AUDIT_SERVICE_LOGGER_TITLE, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Deletion of Audit Logs Completed");

        return responseDTO;
    }
}
