package af.gov.anar.cache.infrastructure.audit;

import af.gov.anar.cache.infrastructure.enumeration.AuditEvent;
import af.gov.anar.cache.infrastructure.enumeration.Components;
import org.springframework.data.auditing.AuditingHandler;

/**
 * The wrapper interface to log the audits
 */
public interface AuditManagerService {

    /**
     * Audits the events across Registration-Client Module.
     * <p>
     * This method takes {@link AuditEvent}, {@link Components}, audit
     * description, refId and refIdType as inputs, other values from Session Context object
     * namely createdBy, sessionUserId and sessionUserName to build the
     * {@link af.gov.anar.lib.audit.builder.AuditRequestBuilder} object. This {@link af.gov.anar.lib.audit.builder.AuditRequestBuilder} object will be passed
     * to the {@link AuditingHandler} which will persist the audit event in
     * database.
     *
     * @param auditEventEnum
     *            this {@code Enum} contains the event details namely eventId,
     *            eventType and eventName
     * @param appModuleEnum
     *            this {@code Enum} contains the application module details namely
     *            moduleId and moduleName
     * @param refId
     *            the ref id of the audit event
     * @param refIdType
     *            the ref id type of the audit event
     */
    void audit(AuditEvent auditEventEnum, Components appModuleEnum, String refId,
               String refIdType);


}