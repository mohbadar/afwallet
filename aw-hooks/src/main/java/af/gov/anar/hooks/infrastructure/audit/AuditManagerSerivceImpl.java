package af.gov.anar.hooks.infrastructure.audit;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import af.gov.anar.hooks.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.hooks.infrastructure.enumeration.AuditEvent;
import af.gov.anar.hooks.infrastructure.enumeration.Components;
import af.gov.anar.hooks.infrastructure.service.BaseService;
import af.gov.anar.hooks.infrastructure.service.HostService;
import af.gov.anar.hooks.infrastructure.service.UserService;
import af.gov.anar.hooks.infrastructure.util.LoggerFactory;
import af.gov.anar.lang.infrastructure.exception.common.ExceptionUtils;
import af.gov.anar.lib.audit.builder.AuditRequestBuilder;
import af.gov.anar.lib.audit.data.AuditRequestDto;
import af.gov.anar.lib.audit.handler.AuditHandler;
import af.gov.anar.lib.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Class to Audit the events of Client.
 * <p>
 * This class creates a wrapper around {@link AuditRequestBuilder} class. This
 * class creates a {@link AuditRequestBuilder} object for each audit event and
 * persists the same using {@link AuditHandler} .
 *
 */
@Service
public class AuditManagerSerivceImpl extends BaseService implements AuditManagerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditManagerSerivceImpl.class);

    @Autowired
    private AuditHandler<AuditRequestDto> auditHandler;

//    @Autowired
//    private AuditHandlerImpl auditHandler;

    @Value(ApplicationGenericConstants.AUDIT_LOG_DELETION_CONFIGURED_DAYS)
    private String auditLogDeletionConfiuredDays;


    @Autowired
    private HostService hostService;

    @Autowired
    private UserService userService;


    /*
     * (non-Javadoc)
     *
     * @see iAuditFactory#audit(AuditEvent, Components,
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
            LOGGER.info("SERVICETE-TEMPLATE-AUDIT_FACTORY-AUDIT", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                    ExceptionUtils.getStackTrace(unknownHostException));
        }

        AuditRequestBuilder auditRequestBuilder = new AuditRequestBuilder();
        auditRequestBuilder.setActionTimeStamp(LocalDateTime.now(ZoneOffset.UTC))
                .setApplicationId(String.valueOf(ApplicationGenericConstants.APPLICATION_ID))
                .setApplicationName(String.valueOf(ApplicationGenericConstants.APPLICATION_NAME))
                .setCreatedBy(userService.getPreferredUsername())
                .setDescription(auditEventEnum.getDescription())
                .setEventId(auditEventEnum.getId())
                .setEventName(auditEventEnum.getName())
                .setEventType(auditEventEnum.getType())
                .setHostIp(hostIP)
                .setHostName(hostName)
                .setId(refId)
                .setIdType(refIdType)
                .setModuleId(appModuleEnum.getId())
                .setModuleName(appModuleEnum.getName())
                .setSessionUserId(userService.getId())
                .setSessionUserName(userService.getPreferredUsername());
        auditHandler.addAudit(auditRequestBuilder.build());
    }

}
