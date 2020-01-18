package af.gov.anar.template.infrastructure.audit.control.service;

import java.sql.Timestamp;

/**
 * Interface to retrieve the start and end {@link Timestamp} of the audit logs
 * sent along with the packet
 */
public interface RegistrationAuditDates {

    Timestamp getAuditLogFromDateTime();
    Timestamp getAuditLogToDateTime();

}
